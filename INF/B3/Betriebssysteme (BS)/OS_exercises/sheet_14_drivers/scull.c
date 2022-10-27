/****************************************************/
/* SCULL Version für den 2.6er Kernel               */
/* Geaendert durch Korbinian Hammer                 */
/* Aenderung: 17.1.14, L. Frank                     */
/* English translation and adaption to kernel 4.15: */
/* Pascal Zimmermann, 2018-12-04                    */
/****************************************************/

/////////////////////////////////////////////////////////////////////////////////////////////////////////

#include <linux/module.h>
#include <linux/init.h>
#include <linux/fs.h>
#include <linux/uaccess.h>
#include <asm/string.h>
#include <linux/slab.h>

/////////////////////////////////////////////////////////////////////////////////////////////////////////

#define DRIVER_NAME "scull"
#define MAX_DEVICES 2

/////////////////////////////////////////////////////////////////////////////////////////////////////////

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Korbinian Hammer");
MODULE_DESCRIPTION("Scull v. 2");
MODULE_SUPPORTED_DEVICE("none");

/////////////////////////////////////////////////////////////////////////////////////////////////////////

static int scull_open(struct inode* inode, struct file* f);
static int scull_release(struct inode* inode, struct file* f);
static ssize_t scull_read(struct file* f, char __user* buf, size_t count, loff_t* f_pos);
static ssize_t scull_write(struct file* f, const char __user* buf, size_t count, loff_t* f_pos);

/////////////////////////////////////////////////////////////////////////////////////////////////////////

unsigned int SCULL_SIZE = 1000;
module_param(SCULL_SIZE, int, 0);

static int major_number;

/////////////////////////////////////////////////////////////////////////////////////////////////////////

static struct file_operations fops = {
    .open    = scull_open,
    .release = scull_release,
    .write   = scull_write,
    .read    = scull_read
};

/////////////////////////////////////////////////////////////////////////////////////////////////////////

typedef struct
{
    char* array;
    int position;
} scull;

scull scull_dev[MAX_DEVICES];

/////////////////////////////////////////////////////////////////////////////////////////////////////////

// scull_open
static int scull_open(struct inode* inode, struct file* f)
{
    int minor = MINOR(inode->i_rdev);
    if((minor < 0) || (minor >= MAX_DEVICES))
    {
        printk("Unknown device!\n");
        return -ENODEV;
    }

    printk("Opening of %s\n", DRIVER_NAME);

    if(f == NULL)
    {
        printk("Error at accessing f!");
        return -ENODEV;
    }

    try_module_get(THIS_MODULE);
    f->private_data = &scull_dev[minor];

    return 0;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////


// scull_release
static int scull_release(struct inode* inode, struct file* f)
{
    printk("Closing of %s\n", DRIVER_NAME);

    module_put(THIS_MODULE);

    return 0;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////


// scull_read
static ssize_t scull_read(struct file* f, char __user* buf, size_t count, loff_t* f_pos)
{
    printk("Reading of %s\n", DRIVER_NAME);

    if(f == NULL)
    {
        printk("Error at accessing f!");
        return -ENODEV;
    }

    scull* dev = (scull*)f->private_data;

    if(count > dev->position)
    {
        count = dev->position;
    }

    if(copy_to_user(buf, dev->array, count) != 0)
    {
        printk("Error at copy_to_user\n");
        return -EFAULT;
    }
    
    *f_pos -= count;
    dev->position -= count;
    f->private_data = dev;

    return count;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////


// scull_write
static ssize_t scull_write(struct file* f, const char __user* buf, size_t count, loff_t* f_pos)
{
    printk("Writing of %s\n", DRIVER_NAME);

    if(f == NULL)
    {
        printk("Error at accessing flip!");
        return -ENODEV;
    }

    scull* dev = (scull *)f->private_data;

    if(count + dev->position >= SCULL_SIZE)
    {
        count = SCULL_SIZE - dev->position;
        if(count == 0)
        {
            printk("%s is full!\n", DRIVER_NAME);
            return -ENOMEM;
        }
    }

    if(copy_from_user(dev->array + dev->position, buf, count) != 0)
    {
        printk("Error at copy_from_user!\n");
        return -EFAULT;
    }

    *f_pos += count;
    dev->position += count;
    f->private_data = dev;

    return count;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////


// scull_init
static int __init scull_init(void)
{
    printk("Initializing %s\n", DRIVER_NAME);
    printk("Size of SCULL_SIZE: %i\n", SCULL_SIZE);

    major_number = register_chrdev(0, DRIVER_NAME, &fops);

    if(major_number < 0)
    {
        printk(KERN_WARNING "%s: Device could not be registered!\n", DRIVER_NAME);
        return major_number;
    }

    printk("Major number: %d\n", major_number);

    for(int i = 0; i < MAX_DEVICES; ++i)
    {
        if(!(scull_dev[i].array = (char *) kmalloc(SCULL_SIZE * sizeof(char), GFP_KERNEL)))
        {
            printk("Could not allocate space");

            for(int e = 0; e < i; ++e)
            {
                kfree(scull_dev[e].array);
            }
            unregister_chrdev(major_number, DRIVER_NAME);
            return -EFAULT;
        }

        scull_dev[i].position = 0;
    }

    return 0;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////

// scull_exit
static void __exit scull_exit(void)
{
    printk("Exiting %s\n", DRIVER_NAME);
    
    for(int i = 0; i < MAX_DEVICES; ++i)
    {
        kfree(scull_dev[i].array);
        scull_dev[i].array = NULL;
    }

    unregister_chrdev(major_number, DRIVER_NAME);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////

module_init(scull_init);
module_exit(scull_exit);

/////////////////////////////////////////////////////////////////////////////////////////////////////////

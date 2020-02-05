#Makefile to build scull driver module

obj-m		:= scull.o
#ccflags-y   := -std=gnu11 -Wdeclaration-after-statement
ccflags-y   := -std=gnu11 -Wall -Wno-declaration-after-statement

KDIR		:= /lib/modules/$(shell uname -r)/build
PWD			:= $(shell pwd)


all:
	$(MAKE) -C $(KDIR) M=$(PWD) modules

clean:
	$(MAKE) -C $(KDIR) M=$(PWD) clean

#https://elixir.bootlin.com/linux/latest/source/Documentation/kbuild/makefiles.txt#L286

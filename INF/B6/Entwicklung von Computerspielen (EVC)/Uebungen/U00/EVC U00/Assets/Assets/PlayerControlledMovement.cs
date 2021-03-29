using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerControlledMovement : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    
    void Update()
    {
        handlePlayerMovement();
    }

    void handlePlayerMovement()
    {
        float LrInput = Input.GetAxis("Horizontal");
        float FbInput = Input.GetAxis("Vertical");
        handleLeftRight(LrInput);
        handleForwardsBackwards(FbInput);
    }

    void handleLeftRight(float input)
    {
        if (input > 0.00)
        {
            transform.position = new Vector3(transform.position.x - 2 * Time.deltaTime * input, transform.position.y, transform.position.z);
        }
        else if (input < 0.00)
        {
            transform.position = new Vector3(transform.position.x - 2 * Time.deltaTime * input, transform.position.y, transform.position.z);
        }
    }

    void handleForwardsBackwards(float input)
    {
        if (input > 0.00)
        {
            transform.position = new Vector3(transform.position.x, transform.position.y, transform.position.z - 2 * Time.deltaTime * input);
        }
        else if (input < 0.00)
        {
            transform.position = new Vector3(transform.position.x, transform.position.y, transform.position.z - 2 * Time.deltaTime * input);
        }
    }
}

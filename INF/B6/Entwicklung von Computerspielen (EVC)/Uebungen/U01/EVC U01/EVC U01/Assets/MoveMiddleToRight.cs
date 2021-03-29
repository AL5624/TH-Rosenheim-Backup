using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveMiddleToRight : MonoBehaviour
{
    private int moveSpeed = -2;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.position.x < -3.66)
        {
            moveSpeed = 2;
        }
        else if (transform.position.x > -0.67)
        {
            moveSpeed = -2;
        }
        transform.position = new Vector3(transform.position.x + moveSpeed * Time.deltaTime, transform.position.y, transform.position.z);
    }
}

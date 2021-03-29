using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveMiddleToLeft : MonoBehaviour
{
    private int moveSpeed = 2;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.position.x < 1.42)
        {
            moveSpeed = 2;
        }
        else if (transform.position.x > 4.3)
        {
            moveSpeed = -2;
        }
        transform.position = new Vector3(transform.position.x + moveSpeed * Time.deltaTime, transform.position.y, transform.position.z);
    }
}

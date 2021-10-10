using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ForthAndBack : MonoBehaviour
{
    private float startPos;
    private int moveSpeed = 2;
    private float yCorrection = -0.5f;
    // Start is called before the first frame update
    void Start()
    {
        startPos = transform.position.z;
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.position.z < startPos - 5)
        {
            yCorrection = -yCorrection;
            moveSpeed = 2;
        }
        else if (transform.position.z > startPos + 5)
        {
            yCorrection = -yCorrection;
            moveSpeed = -2;
        }
        transform.position = new Vector3(transform.position.x, transform.position.y + yCorrection * Time.deltaTime, transform.position.z + moveSpeed * Time.deltaTime);
    }
}

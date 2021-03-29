using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraMotor : MonoBehaviour
{
    public Transform lookAt;

    private readonly Vector3 VECTOR3_ZERO = Vector3.zero;

    private void LateUpdate()
    {
        Vector3 delta = VECTOR3_ZERO;
        float dx = lookAt.position.x - transform.position.x;
        if (dx > 2 || dx < -2)
        {
            if (lookAt.position.x > transform.position.x)
            {
                delta.x = dx;
            }
            else
            {
                delta.x = dx;
            }
        }

        float dz = lookAt.position.z - transform.position.z;
        if (dz > 2 || dz < -2)
        {
            if (lookAt.position.z > transform.position.z)
            {
                delta.z = dz;
            }
            else
            {
                delta.z = dz;
            }
        }

        // delta.y = lookAt.position.y - transform.position.y;
        // delta.z = lookAt.position.z - transform.position.z;

        transform.position = transform.position + delta;
    }
}

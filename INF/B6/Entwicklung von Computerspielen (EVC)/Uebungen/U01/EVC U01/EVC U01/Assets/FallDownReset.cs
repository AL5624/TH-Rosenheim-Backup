using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FallDownReset : MonoBehaviour
{
    public Transform ResetPosition;

    public void OnTriggerEnter(Collider other)
    {
        other.transform.position = ResetPosition.position;
    }
}

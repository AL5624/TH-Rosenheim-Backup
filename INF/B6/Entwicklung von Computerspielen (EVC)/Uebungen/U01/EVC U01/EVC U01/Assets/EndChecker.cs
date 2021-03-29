using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EndChecker : MonoBehaviour
{
    public Transform ResetPosition;
    public Material[] randomMaterials;

    void Start()
    {
        gameObject.GetComponent<Renderer>().material = randomMaterials[Random.Range(0, randomMaterials.Length)];
    }

    public void OnTriggerEnter(Collider other)
    {
        if (!(other.GetComponent<Renderer>().material.name == gameObject.GetComponent<Renderer>().material.name))
        {
            other.transform.position = ResetPosition.position;
            gameObject.GetComponent<Renderer>().material = randomMaterials[Random.Range(0, randomMaterials.Length)];
        }
        else
        {
            Destroy(other.gameObject);
            Debug.Log("Win");
        }
    }
}

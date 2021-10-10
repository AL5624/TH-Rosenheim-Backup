using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Painter : MonoBehaviour
{
    [SerializeField] private Material materialToPaint;

    public void OnTriggerEnter(Collider other)
    {
        other.GetComponent<Renderer>().material = materialToPaint;
    }
}

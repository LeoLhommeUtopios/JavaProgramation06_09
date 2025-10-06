package org.example.DemoSealed;

public sealed abstract class Mere permits Fille,Fille2 {

    abstract void methode1();
    abstract void methode2();
    abstract void methode3();
}

package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.ProjectTesting;

public class BulletContactListener extends ProjectTesting implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa==null || fb==null) return;

        if(fa.getUserData() == null || fb.getUserData() == null){
            return;
        }

        System.out.println("Collision detected");
        System.out.println(fa.getUserData());
        System.out.println(fb.getUserData());


        if(fa.getUserData() == "ground" && fb.getUserData() == "BULLET" ){
            System.out.println("Bullet hit ground");
            GamePlayScreen.collisionToGround();
        }
        if(fa.getUserData() == "enemy" && fb.getUserData() == "BULLET"){
            System.out.println("Bullet hit enemy");
        }


//        if(fa.getUserData() == "ground" && fb.getUserData() == "BULLET" ){
//            System.out.println("Bullet hit ground");
//            GamePlayScreen.collisionToGround();
//        }
        if(fa.getUserData() == "player1" && fb.getUserData() == "BULLET" ){
            System.out.println("Bullet hit player1");
        }





    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa==null || fb==null) return;
        if (fa.getUserData() == null || fb.getUserData() == null) {
            return;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}

package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.ProjectTesting;

import java.io.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;


public class GamePlayScreen implements Screen {

    private final ProjectTesting app;
    private static World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;


    private Image health1;
    private Image health2;
    private Image health3;
    private Image health4;
    private Image health5;
    private Image health6;
    private Image health7;
    private Image health2_1;
    private Image health2_2;
    private Image health2_3;
    private Image health2_4;
    private Image health2_5;
    private Image health2_6;
    private Image health2_7;
    private Image saveSuccess;
    private Image aim1;
    private Image aim2;
    private Image weapon;
    private Image weapon2;
    private Image P1weapon;
    private Image P2weapon;
    private Image pause;
    private Image resume;
    private Image Save;
    private Image Exit;

    private Vector2 tank1movement = new Vector2();

    private Vector2 tank2movement = new Vector2();

    private Vector2 tank1aim = new Vector2();
    private Vector2 tank2aim = new Vector2();

    private float speed = 75000000;


    private static Stage stage;
    private Image GameplayPic;
    private Image Menubutton;
    private Image close;
    private Image close2;

    private static Body box1;

    private static Body box2;
    private static Body bullet1;
    private static Body bullet2;

    private ImageButton fire;
    private static ImageButton fire2;
    private ImageButton blueBullet1;
    private ImageButton blueBullet2;
    private ImageButton blueBullet3;
    private ImageButton greenBullet1;
    private ImageButton greenBullet2;
    private ImageButton greenBullet3;
    private ImageButton yellowBullet1;
    private ImageButton yellowBullet2;
    private ImageButton yellowBullet3;
    float tank1PositionX;
    float tank1PositionY;
    float tank2PositionX;
    float tank2PositionY;


    static final FixtureDef fixtureDef = new FixtureDef();
    static final BodyDef bodyDef = new BodyDef();


    private Texture PauseMenu;
    private Sprite player1;
    private Sprite player2;
    private SpriteBatch batch;
    private Array<Body> tmpBodies = new Array<Body>();
    InputMultiplexer multiplexer = new InputMultiplexer();
    public GamePlayScreen(final ProjectTesting app){
        this.app = app;
        world = new World(new Vector2(0,-9.8f), true);
        this.world.setContactListener(new BulletContactListener());
        stage = new Stage(new FillViewport(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT, app.camera));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[] {
                new Vector2(-1285,-101),
                new Vector2(-1200,-94),
                new Vector2(-1160,-75),
                new Vector2(-1120,-50),
                new Vector2(-1080,-25),
                new Vector2(-1040,5),
                new Vector2(-985,17),
                new Vector2(-373,31),
                new Vector2(-290,100),
                new Vector2(-200,145),
                new Vector2(-100,145),
                new Vector2(100,145),
                new Vector2(370,145),
                new Vector2(430,115),
                new Vector2(500,65),
                new Vector2(570,28),
                new Vector2(640, 20),
                new Vector2(710, 25),
                new Vector2(1285, 25),
        });

        //fixture definition
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0;


        world.createBody(bodyDef).createFixture(fixtureDef).setUserData("ground");
        multiplexer.addProcessor(stage);

        batch = new SpriteBatch();

        Texture GameScreen = new Texture(Gdx.files.internal("GameplayImage_cleanup.png"));
        GameplayPic = new Image(GameScreen);
        GameplayPic.setPosition(0 , 0 );
        GameplayPic.setSize(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT);


        Texture MenuButton = new Texture(Gdx.files.internal("MenuButton-removebg-preview.png"));
        Menubutton = new Image(MenuButton);
        Menubutton.setPosition(20,625);
        Menubutton.setSize(120, 100);



        Texture p1weapon = new Texture(Gdx.files.internal("P1Cweapon.png"));
        P1weapon = new Image(p1weapon);
        P1weapon.setPosition(20 , 5 );
        P1weapon.setSize(300, 300);

        Texture p2weapon = new Texture(Gdx.files.internal("P2Cweapon.png"));
        P2weapon = new Image(p2weapon);
        P2weapon.setPosition(1000 , 5 );
        P2weapon.setSize(300, 300);

        Texture displayweapon = new Texture(Gdx.files.internal("Weapons.png"));
        weapon = new Image(displayweapon);
        weapon.setPosition(450 , 150 );
        weapon.setSize(379, 362);

        Texture displayweapon2 = new Texture(Gdx.files.internal("Weapons2.png"));
        weapon2 = new Image(displayweapon2);
        weapon2.setPosition(450 , 150 );
        weapon2.setSize(408, 444);

        Texture CLOSE = new Texture(Gdx.files.internal("Delete-Red-X-Button-Transparent.png"));
        close = new Image(CLOSE);
        close.setPosition(850, 500);
        close.setSize(60, 60);

        Texture CLOSE2 = new Texture(Gdx.files.internal("Delete-Red-X-Button-Transparent.png"));
        close2 = new Image(CLOSE2);
        close2.setPosition(900, 500);
        close2.setSize(60, 60);

        //Player 1 Fuel
        Texture HEALTH = new Texture(Gdx.files.internal("GreenFuel.png"));
        health1 = new Image(HEALTH);
        health1.setPosition(20 , 5 );
        health1.setSize(341, 69);

        Texture HEALTH_2 = new Texture(Gdx.files.internal("HealthBar.png"));
        health2 = new Image(HEALTH_2);
        health2.setPosition(20 , 5 );
        health2.setSize(341, 69);

        Texture HEALTH_3 = new Texture(Gdx.files.internal("FUEL3.png"));
        health3 = new Image(HEALTH_3);
        health3.setPosition(20 , 5 );
        health3.setSize(341, 69);

        Texture HEALTH_4 = new Texture(Gdx.files.internal("FUEL4.png"));
        health4 = new Image(HEALTH_4);
        health4.setPosition(20 , 5 );
        health4.setSize(341, 69);

        Texture HEALTH_5 = new Texture(Gdx.files.internal("FUEL5.png"));
        health5 = new Image(HEALTH_5);
        health5.setPosition(20 , 5 );
        health5.setSize(341, 69);

        Texture HEALTH_6 = new Texture(Gdx.files.internal("FUEL6.png"));
        health6 = new Image(HEALTH_6);
        health6.setPosition(20 , 5 );
        health6.setSize(341, 69);

        Texture HEALTH_7 = new Texture(Gdx.files.internal("FUEL7.png"));
        health7 = new Image(HEALTH_7);
        health7.setPosition(20 , 5 );
        health7.setSize(341, 69);


        //Player 2 fuel
        Texture HEALTH2 = new Texture(Gdx.files.internal("GreenFuel.png"));
        health2_1 = new Image(HEALTH2);
        health2_1.setPosition(900 , 8 );
        health2_1.setSize(341, 69);

        Texture HEALTH2_2 = new Texture(Gdx.files.internal("HealthBar.png"));
        health2_2 = new Image(HEALTH2_2);
        health2_2.setPosition(900 , 8 );
        health2_2.setSize(341, 69);

        Texture HEALTH2_3 = new Texture(Gdx.files.internal("FUEL3.png"));
        health2_3 = new Image(HEALTH2_3);
        health2_3.setPosition(900 , 8 );
        health2_3.setSize(341, 69);

        Texture HEALTH2_4 = new Texture(Gdx.files.internal("FUEL4.png"));
        health2_4 = new Image(HEALTH2_4);
        health2_4.setPosition(900 , 8 );
        health2_4.setSize(341, 69);

        Texture HEALTH2_5 = new Texture(Gdx.files.internal("FUEL5.png"));
        health2_5 = new Image(HEALTH2_5);
        health2_5.setPosition(900 , 8 );
        health2_5.setSize(341, 69);

        Texture HEALTH2_6 = new Texture(Gdx.files.internal("FUEL6.png"));
        health2_6 = new Image(HEALTH2_6);
        health2_6.setPosition(900 , 8 );
        health2_6.setSize(341, 69);

        Texture HEALTH2_7 = new Texture(Gdx.files.internal("FUEL7.png"));
        health2_7 = new Image(HEALTH2_7);
        health2_7.setPosition(900 , 8 );
        health2_7.setSize(341, 69);

        /*Texture AIM1 = new Texture(Gdx.files.internal("sniper-297661_1280.png"));
        aim1 = new Image(AIM1);
        aim1.setPosition(330 , 430 );
        aim1.setSize(35, 35);*/

        Texture SAVE = new Texture(Gdx.files.internal("Play.png"));
        saveSuccess = new Image(SAVE);
        saveSuccess.setPosition(520 , -60 );
        saveSuccess.setSize(300, 300);







        stage.addActor(GameplayPic);
       // stage.addActor(aim1);

        //Player1 tanks
        if(PlayScreen.INDEX == 0){
            player1 = new Sprite(new Texture(Gdx.files.internal("blueTank.jpg")));
            player1.setSize(165, 165);

            blueBullet1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlueBullet1.jpeg")))));
            blueBullet1.setPosition(50, 75);
            blueBullet1.setSize(66, 56);

            blueBullet2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlueBullet2.jpeg")))));
            blueBullet2.setPosition(120, 75);
            blueBullet2.setSize(66, 56);

            blueBullet3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlueBullet3.jpeg")))));
            blueBullet3.setPosition(190, 75);
            blueBullet3.setSize(66, 56);
        }
        else if(PlayScreen.INDEX == 1){
            player1 = new Sprite(new Texture(Gdx.files.internal("GreenTank.jpg")));
            player1.setSize(165, 165);

            greenBullet1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenTank1.jpeg")))));
            greenBullet1.setPosition(50, 75);
            greenBullet1.setSize(66, 56);

            greenBullet2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenTank2.jpeg")))));
            greenBullet2.setPosition(120, 75);
            greenBullet2.setSize(66, 56);

            greenBullet3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenTank3.jpeg")))));
            greenBullet3.setPosition(190, 75);
            greenBullet3.setSize(66, 56);


        }
        else if(PlayScreen.INDEX == 3){
            player1 = new Sprite(new Texture(Gdx.files.internal("YellowTank.jpg")));
            player1.setSize(165, 165);

            yellowBullet1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("YellowTank1.jpeg")))));
            yellowBullet1.setPosition(50, 75);
            yellowBullet1.setSize(66, 56);

            yellowBullet2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("YellowTank2.jpeg")))));
            yellowBullet2.setPosition(120, 75);
            yellowBullet2.setSize(66, 56);

            yellowBullet3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("YellowTank3.jpeg")))));
            yellowBullet3.setPosition(190, 75);
            yellowBullet3.setSize(66, 56);

        }


        //Player2 tanks
        if(Player2Choose.INDEX2 == 0){
            player2 = new Sprite(new Texture(Gdx.files.internal("OPPblueTank.png")));
            player2.setSize(165, 165);

            blueBullet1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlueBullet1.jpeg")))));
            blueBullet1.setPosition(50, 75);
            blueBullet1.setSize(66, 56);

            blueBullet2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlueBullet2.jpeg")))));
            blueBullet2.setPosition(120, 75);
            blueBullet2.setSize(66, 56);

            blueBullet3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("BlueBullet3.jpeg")))));
            blueBullet3.setPosition(190, 75);
            blueBullet3.setSize(66, 56);
        }
        else if(Player2Choose.INDEX2 == 1){
            player2 = new Sprite(new Texture(Gdx.files.internal("OpponentTank.png")));
            player2.setSize(165, 165);

            greenBullet1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenTank1.jpeg")))));
            greenBullet1.setPosition(50, 75);
            greenBullet1.setSize(66, 56);

            greenBullet2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenTank2.jpeg")))));
            greenBullet2.setPosition(120, 75);
            greenBullet2.setSize(66, 56);

            greenBullet3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GreenTank3.jpeg")))));
            greenBullet3.setPosition(190, 75);
            greenBullet3.setSize(66, 56);
        }
        else if(Player2Choose.INDEX2 == 3){
            player2 = new Sprite(new Texture(Gdx.files.internal("OPPYellowTank.png")));
            player2.setSize(165, 165);
        }


        stage.addActor(P1weapon);
        stage.addActor(P2weapon);
        stage.addActor(health1);
        //stage.addActor(health2);


    }

    public int i = 0;
    @Override
    public void show() {

        multiplexer.addProcessor(stage);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);

        multiplexer.addProcessor(new InputController(){

            @Override
            public boolean keyDown(int keycode) {

                switch(keycode){
                    case Input.Keys.A:
                        System.out.println("A");
                        i+=1;
                        if(i == 1){
                            stage.addActor(health1);
                        }
                        else if(i == 2){
                            health1.remove();
                            stage.addActor(health2);
                        }
                        else if(i == 3){
                            health2.remove();
                            stage.addActor(health3);
                        }
                        else if(i == 4){
                            health3.remove();
                            stage.addActor(health4);
                        }
                        else if(i == 5){
                            health4.remove();
                            stage.addActor(health5);
                        }
                        else if(i == 6){
                            health5.remove();
                            stage.addActor(health6);
                        }
                        else if(i == 7){
                            health6.remove();
                            stage.addActor(health7);
                            i = 0;
                        }
                        tank1movement.x = -speed;
                        break;
                    case Input.Keys.D:
                        System.out.println("D");
                        i+=1;
                        if(i == 1){
                            stage.addActor(health1);
                        }
                        else if(i == 2){
                            health1.remove();
                            stage.addActor(health2);
                        }
                        else if(i == 3){
                            health2.remove();
                            stage.addActor(health3);
                        }
                        else if(i == 4){
                            health3.remove();
                            stage.addActor(health4);
                        }
                        else if(i == 5){
                            health4.remove();
                            stage.addActor(health5);
                        }
                        else if(i == 6){
                            health5.remove();
                            stage.addActor(health6);
                        }
                        else if(i == 7){
                            health6.remove();
                            stage.addActor(health7);
                            i = 0;
                        }
                        tank1movement.x = speed;
                        break;
                    case Input.Keys.LEFT:
                        System.out.println("LEFT");
                        i+=1;
                        if(i == 1){
                            stage.addActor(health2_1);
                        }
                        else if(i == 2){
                            health2_1.remove();
                            stage.addActor(health2_2);
                        }
                        else if(i == 3){
                            health2_2.remove();
                            stage.addActor(health2_3);
                        }
                        else if(i == 4){
                            health2_3.remove();
                            stage.addActor(health2_4);
                        }
                        else if(i == 5){
                            health2_4.remove();
                            stage.addActor(health2_5);
                        }
                        else if(i == 6){
                            health2_5.remove();
                            stage.addActor(health2_6);
                        }
                        else if(i == 7){
                            health2_6.remove();
                            stage.addActor(health2_7);
                            i = 0;
                        }
                        tank2movement.x = -speed;
                        break;
                    case Input.Keys.RIGHT:
                        System.out.println("RIGHT");
                        i+=1;
                        if(i == 1){
                            stage.addActor(health2_1);
                        }
                        else if(i == 2){
                            health2_1.remove();
                            stage.addActor(health2_2);
                        }
                        else if(i == 3){
                            health2_2.remove();
                            stage.addActor(health2_3);
                        }
                        else if(i == 4){
                            health2_3.remove();
                            stage.addActor(health2_4);
                        }
                        else if(i == 5){
                            health2_4.remove();
                            stage.addActor(health2_5);
                        }
                        else if(i == 6){
                            health2_5.remove();
                            stage.addActor(health2_6);
                        }
                        else if(i == 7){
                            health2_6.remove();
                            stage.addActor(health2_7);
                            i = 0;
                        }
                        tank2movement.x = speed;
                        break;

                }
                return false;
            }
            @Override
            public boolean keyUp(int keycode) {
                switch(keycode){
                    case Input.Keys.A:
                    case Input.Keys.D:
                        tank1movement.x = 0;
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.RIGHT:
                        tank2movement.x = 0;
                        break;
                }
                return false;
            }
        });

        Gdx.input.setInputProcessor(multiplexer);

        //BOX

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        if (ProjectTesting.isLoad == false) {
            bodyDef.position.set(-10f*68, 10f*20);
            bodyDef.position.set(10f*93, 10f*20);
        } else {
            try{
                ProjectTesting.isLoad = false;
                BufferedReader br = new BufferedReader(new FileReader("save.txt"));
                String line = br.readLine();
                String[] tokens = line.split(" ");
                bodyDef.position.set(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]));
                line = br.readLine();
                tokens = line.split(" ");
                bodyDef.position.set(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        //BOX SHAPE
        CircleShape boxShape = new CircleShape();
        boxShape.setRadius(40);


        //fixture definition
        fixtureDef.shape = boxShape;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.0001f;
        fixtureDef.density = 200;

        box1 = world.createBody(bodyDef);
        box1.createFixture(fixtureDef).setUserData("player1");
        box1.setUserData(player1);

//        boxShape.dispose();



        //BOX2




        //BOX SHAPE
        CircleShape boxShape2 = new CircleShape();
        boxShape2.setRadius(40);


        //fixture defintion
        fixtureDef.shape = boxShape2;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.density = 200;


        box2 = world.createBody(bodyDef);
        box2.createFixture(fixtureDef).setUserData("enemy");
        box2.setUserData(player2);


//        boxShape2.dispose();



        //Fire button
        fire = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("FIRE.png")))));
        fire.setSize(300,300);
        fire.setPosition(35, 50);

        fire.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //Bullet
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(box1.getPosition().x+10, box1.getPosition().y+10);

                //bullet fixture definition
                CircleShape bulletShape = new CircleShape();
                bulletShape.setRadius(15);

                fixtureDef.shape = bulletShape;
                fixtureDef.friction = 0.75f;
                fixtureDef.restitution = 0.1f;
                fixtureDef.density = 3;

                bullet1 =  world.createBody(bodyDef);
                bullet1.createFixture(fixtureDef);
                bullet1.setUserData("BULLET");

//                bulletShape.dispose();

                //linear impulse to bullet
                bullet1.applyLinearImpulse(500000, 500000, bullet1.getPosition().x, bullet1.getPosition().y, true);

                //fire.remove();
            }
        });

        stage.addActor(fire);

        //fire2 button
        fire2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("FIRE.png")))));
        fire2.setSize(300,300);
        fire2.setPosition(1000, 50);

        fire2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //Bullet
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(box2.getPosition().x+100, box2.getPosition().y+100);

                //bullet fixture definition
                CircleShape bulletShape = new CircleShape();
                bulletShape.setRadius(15);

                fixtureDef.shape = bulletShape;
                fixtureDef.friction = 0.75f;
                fixtureDef.restitution = 0.1f;
                fixtureDef.density = 3;

                bullet2 =  world.createBody(bodyDef);
                bullet2.createFixture(fixtureDef);
                bullet2.setUserData("BULLET");

                bullet2.applyLinearImpulse(-500000, 500000, bullet2.getPosition().x, bullet2.getPosition().y, true);

                //fire2.remove();
            }
        });
        stage.addActor(fire2);


//      groundShape.dispose();
        initButton();
    }


    public void PauseMenu(){
        Texture pauseTexture = new Texture(Gdx.files.internal("SettingsPic.jpg"));
        pause = new Image(pauseTexture);
        pause.setPosition(450,150);
        pause.setSize(430,427);

        stage.addActor(pause);

        Texture Resume = new Texture(Gdx.files.internal("ResumeNew.png"));
        resume = new Image(Resume);
        resume.setPosition(460, 200);
        resume.setSize(400, 400);

        stage.addActor(resume);

        Texture save = new Texture(Gdx.files.internal("SaveGame.png"));
        Save = new Image(save);
        Save.setPosition(460, 110);
        Save.setSize(400, 400);

        stage.addActor(Save);

        Texture quit = new Texture(Gdx.files.internal("MainMenuNew.png"));
        Exit = new Image(quit);
        Exit.setPosition(460, 20);
        Exit.setSize(400, 400);

        stage.addActor(Exit);

        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                pause.remove();
                resume.remove();
                Save.remove();
                Exit.remove();
                //check if saveSuccess is there in stage
                if(saveSuccess != null) {
                    saveSuccess.remove();
                }
            }
        });
        Save.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                /*tank1PositionX = box1.getPosition().x;
                tank1PositionY = box1.getPosition().y;

                tank2PositionX = box2.getPosition().x;
                tank2PositionY = box2.getPosition().y;

                stage.addActor(saveSuccess);*/

                try{
                    FileWriter file = new FileWriter("save.txt");
                    file.write(box1.getPosition().x + " " + box1.getPosition().y + "\n");
                    file.write(box2.getPosition().x + " " + box2.getPosition().y + "\n");
                    file.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.addActor(saveSuccess);


            }
        });
        Exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                app.setScreen(new LoadingScreen(app));
            }
        });

    }


    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();


        initButton();
        world.step(1/60f, 6, 2);
        box1.applyForceToCenter(tank1movement, true);
        box2.applyForceToCenter(tank2movement, true);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        world.getBodies(tmpBodies);
        for(Body body : tmpBodies){
            if(body.getUserData() != null && body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
                //sprite.setRotation((float) Math.toDegrees(body.getAngle()));
                sprite.draw(batch);
            }
        }

        batch.end();

        debugRenderer.render(world, camera.combined);

    }

    public void update(float delta) {
        camera.update();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        world.dispose();
        debugRenderer.dispose();
        player1.getTexture().dispose();
        player2.getTexture().dispose();

    }

    private void initButton(){

        /*stage.addActor(Menubutton);
        Menubutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PauseMenu();
            }
        });*/
        ImageButton PAUSE = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("MenuButton-removebg-preview.png")))));
        PAUSE.setSize(120,100);
        PAUSE.setPosition(10, 625);

        PAUSE.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PauseMenu();
            }
        });
        stage.addActor(PAUSE);
        P1weapon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(PlayScreen.INDEX ==0){
                    stage.addActor(blueBullet1);
                    stage.addActor(blueBullet2);
                    stage.addActor(blueBullet3);
                }
                if(PlayScreen.INDEX ==1){
                    stage.addActor(greenBullet1);
                    stage.addActor(greenBullet2);
                    stage.addActor(greenBullet3);
                }
                if(PlayScreen.INDEX ==2){
                    stage.addActor(yellowBullet3);
                    stage.addActor(yellowBullet2);
                    stage.addActor(yellowBullet3);
                }
                stage.addActor(close);

                close.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(PlayScreen.INDEX ==0){
                            blueBullet1.remove();
                            blueBullet2.remove();
                            blueBullet3.remove();
                        }
                        if(PlayScreen.INDEX ==1){
                            greenBullet1.remove();
                            greenBullet2.remove();
                            greenBullet3.remove();
                        }
                        if(PlayScreen.INDEX ==2){
                            yellowBullet1.remove();
                            yellowBullet2.remove();
                            yellowBullet3.remove();
                        }
                        close.remove();
                    }
                });

            }
        });

        P2weapon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Player2Choose.INDEX2 ==0){
                    stage.addActor(blueBullet1);
                    stage.addActor(blueBullet2);
                    stage.addActor(blueBullet3);
                }
                if(Player2Choose.INDEX2 ==1){
                    stage.addActor(greenBullet1);
                    stage.addActor(greenBullet2);
                    stage.addActor(greenBullet3);
                }
                if(Player2Choose.INDEX2 ==2){
                    stage.addActor(yellowBullet3);
                    stage.addActor(yellowBullet2);
                    stage.addActor(yellowBullet3);
                }
                stage.addActor(close2);

                close2.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(Player2Choose.INDEX2 ==0){
                            blueBullet1.remove();
                            blueBullet2.remove();
                            blueBullet3.remove();
                        }
                        if(Player2Choose.INDEX2 ==1){
                            greenBullet1.remove();
                            greenBullet2.remove();
                            greenBullet3.remove();
                        }
                        if(Player2Choose.INDEX2 ==2){
                            yellowBullet3.remove();
                            yellowBullet2.remove();
                            yellowBullet3.remove();
                        }
                        close2.remove();
                    }
                });
            }
        });


    }

    public static void collisionToGround(){
        world.destroyBody(bullet1);

        //Fire button
        fire2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("FIRE.png")))));
        fire2.setSize(300,300);
        fire2.setPosition(1000, 50);

        fire2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //Bullet
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(box2.getPosition().x+10, box2.getPosition().y+10);

                //bullet fixture definition
                CircleShape bulletShape = new CircleShape();
                bulletShape.setRadius(15);

                fixtureDef.shape = bulletShape;
                fixtureDef.friction = 0.75f;
                fixtureDef.restitution = 0.1f;
                fixtureDef.density = 3;

                bullet1 =  world.createBody(bodyDef);
                bullet1.createFixture(fixtureDef);
                bullet1.setUserData("BULLET");

                bulletShape.dispose();

                //linear impulse to bullet
                bullet1.applyLinearImpulse(500000, 500000, bullet1.getPosition().x, bullet1.getPosition().y, true);

                fire2.remove();
            }
        });

        stage.addActor(fire2);


    }

    private void saveGame(){
        Save.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {


            }
        });
    }
}


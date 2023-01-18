package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.ProjectTesting;

import java.util.ArrayList;

public class PlayScreen implements Screen {

    private Image Playbutton;

    private Image Choosebutton;
    private final ProjectTesting app;

    private Stage stage;
    //private Skin skin;
    private OrthographicCamera camera;
    private Image GarageImage;
    private ArrayList<Image> tanksList = new ArrayList<Image>();
    private Image BluetankImage;
    private Image GreentankImage;
    private Image YellowtankImage;
    private Image leftButton;
    private Image rightButton;
    private int index = 0;

    private Image FrosttitleImage;
    private Image BuratinotitleImage;
    private Image HeliotitleImage;

    private Image Backbutton;
    private Image Player1button;

    public static int INDEX;




    //get tanks from player 1
    public int getTank1() {
        return INDEX;
    }

    public void setTank1(int INDEX) {
        this.INDEX = INDEX;
    }




    public PlayScreen(final ProjectTesting app){
        this.app = app;
        this.stage = new Stage(new FillViewport(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT, app.camera));
        Gdx.input.setInputProcessor(stage);

        Texture garage = new Texture(Gdx.files.internal("GB.jpg"));
        GarageImage = new Image(garage);
        GarageImage.setPosition(0 , 0 );
        GarageImage.setSize(ProjectTesting.V_WIDTH, ProjectTesting.V_HEIGHT);

        Texture blueTank = new Texture(Gdx.files.internal("BlueTank-removebg-preview.jpg"));
        BluetankImage = new Image(blueTank);
        BluetankImage.setPosition(400 , 160 );
        BluetankImage.setSize(473, 200);

        Texture greenTank = new Texture(Gdx.files.internal("GreenTank.jpg"));
        GreentankImage = new Image(greenTank);
        GreentankImage.setPosition(470 , 100 );
        GreentankImage.setSize(400, 400);

        Texture yellowTank = new Texture(Gdx.files.internal("YellowTank.jpg"));
        YellowtankImage = new Image(yellowTank);
        YellowtankImage.setPosition(490 , 50 );
        YellowtankImage.setSize(450, 450);

        tanksList.add(BluetankImage);
        tanksList.add(GreentankImage);
        tanksList.add(YellowtankImage);

        Texture LeftButton = new Texture(Gdx.files.internal("arrow left .png"));
        leftButton = new Image(LeftButton);
        leftButton.setPosition(50 , 200 );
        leftButton.setSize(150, 150);

        Texture RightButton = new Texture(Gdx.files.internal("arrow right.png"));
        rightButton = new Image(RightButton);
        rightButton.setPosition(1100 , 200 );
        rightButton.setSize(150, 150);

        Texture PlayButton = new Texture(Gdx.files.internal("PlayButton.png"));
        Playbutton = new Image(PlayButton);
        Playbutton.setPosition(550 , 50 );
        Playbutton.setSize(200, 100);

        Texture ChooseButton = new Texture(Gdx.files.internal("Choose.png"));
        Choosebutton = new Image(ChooseButton);
        Choosebutton.setPosition(530 , 40 );
        Choosebutton.setSize(200, 100);

        Texture Frosttitle = new Texture(Gdx.files.internal("FrostText.jpg"));
        FrosttitleImage = new Image(Frosttitle);
        FrosttitleImage.setPosition(500 , 400 );
        FrosttitleImage.setSize(300, 100);

        Texture Buratinotitle = new Texture(Gdx.files.internal("Buratino.jpg"));
        BuratinotitleImage = new Image(Buratinotitle);
        BuratinotitleImage.setPosition(500 , 400 );
        BuratinotitleImage.setSize(300, 100);

        Texture Heliotitle = new Texture(Gdx.files.internal("Helios.jpg"));
        HeliotitleImage = new Image(Heliotitle);
        HeliotitleImage.setPosition(500 , 400 );
        HeliotitleImage.setSize(300, 100);

        Texture ChoosePlayer1 = new Texture(Gdx.files.internal("PlayerOneCh.jpg"));
        Player1button = new Image(ChoosePlayer1);
        Player1button.setPosition(500 , 500 );
        Player1button.setSize(280, 80);

        Texture BackButton = new Texture(Gdx.files.internal("BackButton.jpg"));
        Backbutton = new Image(BackButton);
        Backbutton.setPosition(50 , 600 );
        Backbutton.setSize(60, 50);







        Image currentTank = tanksList.get(index);
        stage.addActor(GarageImage);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(tanksList.get(index));

    }


    @Override
    public void show() {
        System.out.println("PLAY");
        Gdx.input.setInputProcessor(stage);



        leftButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("LEFT");

                if(index >= 0){
                    tanksList.get(index).remove();
                    /*if(tanksList.get(index) == BluetankImage){
                        //FrosttitleImage.remove();
                    }*/

                    if(tanksList.get(index) == GreentankImage){
                        BuratinotitleImage.remove();
                    }
                    if(tanksList.get(index) == YellowtankImage){
                        HeliotitleImage.remove();
                    }
                    index--;
                    stage.addActor(tanksList.get(index));
                    if(tanksList.get(index) == BluetankImage){
                        //stage.addActor(BluetankImage);
                        //stage.addActor(FrosttitleImage);
                        leftButton.remove();
                    }

                    if(tanksList.get(index) == GreentankImage){
                        //stage.addActor(BuratinotitleImage);
                        stage.addActor(leftButton);
                        stage.addActor(rightButton);
                    }

                    if(tanksList.get(index) == YellowtankImage){
                        //stage.addActor(HeliotitleImage);
                        rightButton.remove();
                    }

                    if(index == 0){

                        setTank1(index);
                    }

                    else if(index == 1){

                        setTank1(index);
                    }

                    else if(index == 2){

                        setTank1(index);
                    }

                }

            }

        });
        rightButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("Right");
                if(index <= 2){
                    tanksList.get(index).remove();
                    /*if(tanksList.get(index) == BluetankImage){
                        FrosttitleImage.remove();
                    }

                    if(tanksList.get(index) == GreentankImage){
                        BuratinotitleImage.remove();
                        stage.addActor(leftButton);
                    }
                    if(tanksList.get(index) == YellowtankImage){
                        HeliotitleImage.remove();
                    }*/
                    index++;
                    stage.addActor(tanksList.get(index));
                    if(tanksList.get(index) == BluetankImage){
                        leftButton.remove();
                        //stage.addActor(FrosttitleImage);


                    }

                    if(tanksList.get(index) == GreentankImage){
                        stage.addActor(leftButton);
                        //stage.addActor(BuratinotitleImage);

                    }

                    if(tanksList.get(index) == YellowtankImage){
                        rightButton.remove();
                        //stage.addActor(HeliotitleImage);

                    }

                    if(index == 0){

                        setTank1(index);
                    }

                    else if(index == 1){

                        setTank1(index);
                    }

                    else if(index == 2){

                        setTank1(index);
                    }
                }
            }
        });
        initButton();
    }

    @Override
    public void render(float delta) {
        update(delta);
        stage.draw();

        app.batch.begin();
        app.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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
    }

    private void update(float delta) {
        stage.act(delta);
    }

    private void initButton(){

        /*Choosebutton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("CHOOSE");
                app.setScreen(new Player2Choose(app));

            }
        });
        stage.addActor(Choosebutton);*/

        ImageButton CHOOSE = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Choose.png")))));
        CHOOSE.setPosition(530,40);
        CHOOSE.setSize(200, 100);
        CHOOSE.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("CHOOSE");
                app.setScreen(new Player2Choose(app));

            }
        });
        stage.addActor(CHOOSE);


        stage.addActor(Backbutton);
        stage.addActor(Player1button);

        Backbutton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("BACK");
                app.setScreen(new LoadingScreen(app));
            }
        });


    }
}

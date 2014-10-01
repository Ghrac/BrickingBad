package brickingbad;

/**
 * BrickingBad
 * 
 * Juego BrickBreaker con imagener basadas en serie Breaking Bad
 * 
 * **Sonidos Originales Namco
 * 
 * @author Diana Robles LAD 1195255 y Rodrigo Marcos 919806 ITC
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;

public class BrickingBad extends JFrame implements Runnable, KeyListener {

    private Image    imaImagenApplet;   // Imagen a proyectar en Applet	
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen
    private int iVidasPlayer;
    private int iVidasXtasis;
    private int iVidasHeroina;
    private int iVidasCocaina;
    private int iVidasMeth;
    private int iVidasEspecial;
    private int iScore;
    private Base basBola;
    private Base basBase;
    private Base basBrickCoca;
    private Base basBrickHero;
    private Base basBrickXsis;
    private Base basBrickMeth;
    private Base basBrickEspecial;
    private Base basEspMasVida;
    private Base basEspMenosVida;
    private Base basEspX2Power;
    private Base basEspX2Vel;
    private Base basEspMasBarra;
    private Base basEspMenosBarra;
    private Image imaBola;
    private Image imaBase;
    private Image imaBolaFuego;
    private Image imaBaseChica;
    private Image imaBaseGrande;
    private Image imaBrickCoca;
    private Image imaBrickHero;
    private Image imaBrickXsis;
    private Image imaBrickMeth;
    private Image imaBrickCocaHalf;
    private Image imaBrickHeroHalf;
    private Image imaBrickXsisHalf;
    private Image imaBrickMethHalf;
    private Image imaBrickEspecial;
    private Image imaEspMasVida;
    private Image imaEspMenosVida;
    private Image imaEspX2Power;
    private Image imaEspX2Vel;
    private Image imaEspMasBarra;
    private Image imaEspMenosBarra;
    private Image imaAnimHero1;
    private Image imaAnimHero2;
    private Image imaAnimCoca1;
    private Image imaAnimCoca2;
    private Image imaAnimXsis1;
    private Image imaAnimXsis2;
    private Image imaAnimMeth1;
    private Image imaAnimMeth2;
    private int iDirBola;
    private int iDirBase;
    private int iVelBase;
    private int iLevel;
    private int iBricks;
    private int iRan;
    private boolean bPausa;
    private boolean bSoltarBola;
    private boolean bInstrucciones;
    private boolean bGameStart;
    private boolean bSpace;
    private boolean bEspecial;
    private boolean bEndGame;
    private LinkedList lnkCoca;
    private LinkedList lnkHero;
    private LinkedList lnkXsis;
    private LinkedList lnkMeth;
    private LinkedList lnkEspecial;
    private SoundClip souMusica;
    private SoundClip souIntro;
    private SoundClip souGameStart;
    private SoundClip souBrickCrash;
    private SoundClip souEndGame;
    private Animacion aniHero;
    private Animacion aniCoca;
    private Animacion aniXsis;
    private Animacion aniMeth;
    private long tiempoActualHero;
    private long tiempoActualCoca;
    private long tiempoActualXsis;
    private long tiempoActualMeth;

    public BrickingBad() {
    /** 
     * init
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos
     * a usarse en el <code>Applet</code> y se definen funcionalidades.
     */
        // hago el applet de un tamaño 600,600
        setSize(600, 600);
        
        iVidasPlayer = 5;
        iVidasMeth = 1;
        iVidasCocaina = 1;
        iVidasHeroina = 1;
        iVidasXtasis = 1;
        iDirBola = 0;
        iDirBase = 0;
        iVelBase = 10;
        iScore = 0;
        iLevel = 3;
        iBricks = 0;
        iRan = 0;
        bPausa = true;
        bSoltarBola = false;
        bInstrucciones = true;
        bGameStart = false;
        bSpace = false;
        bEspecial = false;
        bEndGame = false;
        tiempoActualHero = 0;
        tiempoActualCoca = 0;
        tiempoActualXsis = 0;
        tiempoActualMeth = 0;
        
        souMusica = new SoundClip("InGameMusic.wav");
        souMusica.setLooping(true);
        souIntro = new SoundClip("IntroSound.wav");
        souIntro.setLooping(true);
        souIntro.play();
        souGameStart = new SoundClip("GameStart.wav");
        souBrickCrash = new SoundClip("BrickCrash.wav");
        souEndGame = new SoundClip("HighScoreMusic.wav");
        
        
        addKeyListener(this);
        
        // se crea imagen de Base
        imaBase = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("barra.png"));
        // Creo la Base
	basBase = new Base(0, 0, imaBase, iVidasPlayer);
        basBase.setX(((getWidth() / 2) - (basBase.getAncho() / 2)));
        basBase.setY(getHeight() - basBase.getAlto());
        
        imaBaseChica = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("barracorta.png"));
        imaBaseGrande = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("barralarga.png"));
        
        // se crea imagen de Bola
        imaBola = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("pelota_base.png"));
        // Creo la Bola
	basBola = new Base(0, 0, imaBola, 1);
        basBola.setX(700);
        basBola.setVelocidad(6);
        
        imaBolaFuego = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("pelotafire.png"));
        
        //se crea imagen de BasEspMasVida
        imaEspMasVida = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("MasVida.png"));
        // creo objeto
        basEspMasVida = new Base(1000, 0, imaEspMasVida, 0);
        
        //se crea imagen de BasEspMenosVida
        imaEspMenosVida = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("MenosVida.png"));
        // creo objeto
        basEspMenosVida = new Base(1000, 0, imaEspMenosVida, 0);
        
        //se crea imagen de BasEspX2Power
        imaEspX2Power = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("X2Power.png"));
        // creo objeto
        basEspX2Power = new Base(1000, 0, imaEspX2Power, 0);
        
        //se crea imagen de BasEspX2Vel
        imaEspX2Vel = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("X2Vel.png"));
        // creo objeto
        basEspX2Vel = new Base(1000, 0, imaEspX2Vel, 0);
        
        //se crea imagen de BasEspMasBarra
        imaEspMasBarra = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("MasBarra.png"));
        // creo objeto
        basEspMasBarra = new Base(1000, 0, imaEspMasBarra, 0);
        
        //se crea imagen de BasEspMenosBarra
        imaEspMenosBarra = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("MenosBarra.png"));
        // creo objeto
        basEspMenosBarra = new Base(1000, 0, imaEspMenosBarra, 0);
        
        
        imaAnimHero1 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("brickheroinarompe1.png"));
        imaAnimHero2 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("brickheroinarompe2.png"));
        
        aniHero = new Animacion();
        aniHero.sumaCuadro(imaAnimHero1, 1500);
        aniHero.sumaCuadro(imaAnimHero2, 1500);
        
        
        imaAnimCoca1 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("brickcocainarompe1.png"));
        imaAnimCoca2 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("brickcocainarompe2.png"));
        
        aniCoca = new Animacion();
        aniCoca.sumaCuadro(imaAnimCoca1, 1500);
        aniCoca.sumaCuadro(imaAnimCoca2, 1500);
        
        
        imaAnimXsis1 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("brickXtasisrompe1.png"));
        imaAnimXsis2 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("brickXtasisrompe2.png"));
        
        aniXsis = new Animacion();
        aniXsis.sumaCuadro(imaAnimXsis1, 1500);
        aniXsis.sumaCuadro(imaAnimXsis2, 1500);
        
        
        imaAnimMeth1 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("methrompe1.png"));
        imaAnimMeth2 = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("methrompe2.png"));
        
        aniMeth = new Animacion();
        aniMeth.sumaCuadro(imaAnimMeth1, 1500);
        aniMeth.sumaCuadro(imaAnimMeth2, 1500);
        
        
        lnkCoca = new LinkedList();
        lnkHero = new LinkedList();
        lnkXsis = new LinkedList();
        lnkMeth = new LinkedList();
        lnkEspecial = new LinkedList();
        
        
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickEspecial = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickespecial.png"));
            // Creo a BrickCoca1
            basBrickEspecial = new Base(0, 0, imaBrickEspecial, iVidasEspecial);
            basBrickEspecial.setVidas(1);
            basBrickEspecial.setVelocidad(1);
            if(iI == 0) {
                basBrickEspecial.setX(450);
                basBrickEspecial.setY(100);
            }
            else if(iI == 1) {
                basBrickEspecial.setX(350);
                basBrickEspecial.setY(150);
            }
            else if(iI == 2) {
                basBrickEspecial.setX(250);
                basBrickEspecial.setY(200);
            }
            else if(iI == 3) {
                basBrickEspecial.setX(150);
                basBrickEspecial.setY(250);
            }
            lnkEspecial.add(basBrickEspecial);
        }
        
        
        imaBrickCocaHalf = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().
                            getResource("brickcocainacrack.png"));
        
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickCoca = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickcocaina.png"));
            // Creo a BrickCoca1
            basBrickCoca = new Base(0, 0, imaBrickCoca, iVidasCocaina);
            basBrickCoca.setVidas(6);
            if(iI == 0) {
                basBrickCoca.setX(50);
                basBrickCoca.setY(250);
                basBrickCoca.setiNumLista(0);
            }
            else if(iI == 1) {
                basBrickCoca.setX(250);
                basBrickCoca.setY(250);
                basBrickCoca.setiNumLista(1);
            }
            else if(iI == 2) {
                basBrickCoca.setX(350);
                basBrickCoca.setY(250);
                basBrickCoca.setiNumLista(2);
            }
            else if(iI == 3) {
                basBrickCoca.setX(450);
                basBrickCoca.setY(250);
                basBrickCoca.setiNumLista(3);
            }
            lnkCoca.add(basBrickCoca);
        }
        
        imaBrickHeroHalf = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().
                            getResource("brickheroinacrack.png"));
        
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickHero = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickheroina.png"));
            // Creo a BrickCoca1
            basBrickHero = new Base(0, 0, imaBrickHero, iVidasHeroina);
            basBrickHero.setVidas(4);
            if(iI == 0) {
                basBrickHero.setX(50);
                basBrickHero.setY(100);
                basBrickHero.setiNumLista(0);
            }
            else if(iI == 1) {
                basBrickHero.setX(150);
                basBrickHero.setY(100);
                basBrickHero.setiNumLista(1);
            }
            else if(iI == 2) {
                basBrickHero.setX(250);
                basBrickHero.setY(100);
                basBrickHero.setiNumLista(2);
            }
            else if(iI == 3) {
                basBrickHero.setX(350);
                basBrickHero.setY(100);
                basBrickHero.setiNumLista(3);
            }
            lnkHero.add(basBrickHero);
        }
        
        imaBrickXsisHalf = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().
                            getResource("brickxtasiscrack.png"));
        
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickXsis = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickxtasis.png"));
            // Creo a BrickCoca1
            basBrickXsis = new Base(0, 0, imaBrickXsis, iVidasXtasis);
            basBrickXsis.setVidas(2);
            if(iI == 0) {
                basBrickXsis.setX(50);
                basBrickXsis.setY(150);
                basBrickXsis.setiNumLista(0);
            }
            else if(iI == 1) {
                basBrickXsis.setX(150);
                basBrickXsis.setY(150);
                basBrickXsis.setiNumLista(1);
            }
            else if(iI == 2) {
                basBrickXsis.setX(250);
                basBrickXsis.setY(150);
                basBrickXsis.setiNumLista(2);
            }
            else if(iI == 3) {
                basBrickXsis.setX(450);
                basBrickXsis.setY(150);
                basBrickXsis.setiNumLista(3);
            }
            lnkXsis.add(basBrickXsis);
        }
        
        imaBrickMethHalf = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickmethcrack.png"));
        
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickMeth = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickmeth.png"));
            // Creo a BrickCoca1
            basBrickMeth = new Base(0, 0, imaBrickMeth, iVidasMeth);
            basBrickMeth.setVidas(8);
            if(iI == 0) {
                basBrickMeth.setX(50);
                basBrickMeth.setY(200);
                basBrickMeth.setiNumLista(0);
            }
            else if(iI == 1) {
                basBrickMeth.setX(150);
                basBrickMeth.setY(200);
                basBrickMeth.setiNumLista(1);
            }
            else if(iI == 2) {
                basBrickMeth.setX(350);
                basBrickMeth.setY(200);
                basBrickMeth.setiNumLista(2);
            }
            else if(iI == 3) {
                basBrickMeth.setX(450);
                basBrickMeth.setY(200);
                basBrickMeth.setiNumLista(3);
            }
            lnkMeth.add(basBrickMeth);
        }
        
    /** 
     * start
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo
     * para la animacion este metodo es llamado despues del init o 
     * cuando el usuario visita otra pagina y luego regresa a la pagina
     * en donde esta este <code>Applet</code>
     * 
     */
        // Declaras un hilo
        Thread th = new Thread (this);
        // Empieza el hilo
        th.start ();
        
    }
    
    public void run () {
        // se realiza el ciclo del juego mientras jugador tenga vidas
        while (/*(iVidasPlayer > 0) && (iLevel < 4)*/true) {
            /* mientras dure el juego, se actualizan posiciones de jugadores
               se checa si hubo colisiones para desaparecer jugadores o corregir
               movimientos y se vuelve a pintar todo
            */
            //si no esta pausado el juego, puede correr actualiza y checa 
            //colision
            if(!bPausa){
                actualiza();
                checaColision();
            }
            repaint();
            try	{
                // El thread se duerme.
                Thread.sleep (20);
            }
            catch (InterruptedException iexError)	{
                System.out.println("Hubo un error en el juego " + 
                        iexError.toString());
            }
        }
    }
    
    
    /** 
     * actualiza
     * 
     * Metodo que actualiza la posicion del objeto Personaje
     * 
     */
    public void actualiza() {
        
        if(basBrickHero.getVidas() < 1) {
            aniHero.actualiza(tiempoActualHero);
            tiempoActualHero = tiempoActualHero + 1;
        }
        
        if(basBrickCoca.getVidas() < 1) {
            aniCoca.actualiza(tiempoActualCoca);
            tiempoActualCoca = tiempoActualCoca + 1;
        }
        
        if(basBrickXsis.getVidas() < 1) {
            aniXsis.actualiza(tiempoActualXsis);
            tiempoActualXsis = tiempoActualXsis + 1;
        }
        
        if(basBrickMeth.getVidas() < 1) {
            aniMeth.actualiza(tiempoActualMeth);
            tiempoActualMeth = tiempoActualMeth + 1;
        }
        
        basBase.setVelocidad(iVelBase);
        
        if(iBricks == 16) {
            iLevel = iLevel + 1;
            //bNewLevel = true;
            levelUp();
            iBricks = 0;
        }
        
        if(iVidasPlayer < 1) {
            if(!bEndGame) {
                bSoltarBola = false;
                souMusica.stop();
                souEndGame.play();
                bEndGame = true;
            }
        }
        
        if(bInstrucciones){
            bPausa = true;
        }
        
        if(iDirBase == 0) {
            
        }
        else if(iDirBase == 1) {
            basBase.derecha();
        }
        else if(iDirBase == 2) {
            basBase.izquierda();
        }
        
        if(!bSoltarBola) {
            basBola.setX((basBase.getX() + (basBase.getAncho() / 2)) -
                    (basBola.getAncho() / 2));
            basBola.setY(basBase.getY() - basBola.getAlto() - 1);
        }
        else {
            if(iDirBola == 0) {
                bSoltarBola = false;
                basBola.setX((basBase.getX() + (basBase.getAncho() / 2)) -
                    (basBola.getAncho() / 2));
                basBola.setY(basBase.getY() - basBola.getAlto() - 1);
            }
            else if(iDirBola == 1) {
                basBola.derecha();
                basBola.arriba();
            }
            else if(iDirBola == 2) {
                basBola.izquierda();
                basBola.arriba();
            }
            else if(iDirBola == 3) {
                basBola.derecha();
                basBola.abajo();
            }
            else if(iDirBola == 4) {
                basBola.izquierda();
                basBola.abajo();
            }
        }
        
        if(bEspecial) {
            switch(iRan) {
                case 0:
                    basEspMenosVida.setY(basEspMenosVida.getY() + 2);
                    break;
                case 1:
                    basEspMasVida.setY(basEspMasVida.getY() + 2);
                    break;
                case 2:
                    basEspMenosBarra.setY(basEspMenosBarra.getY() + 2);
                    break;
                case 3:
                    basEspMasBarra.setY(basEspMasBarra.getY() + 2);
                    break;
                case 4:
                    basEspX2Vel.setY(basEspX2Vel.getY() + 2);
                    break;
                case 5:
                case 6:
                    basEspX2Power.setY(basEspX2Power.getY() + 2);
                    break;
            }
        }
    }
    
    /**
     * checaColision
     * 
     * Metodo usado para checar la colision del objeto Personaje
     * con las orillas del <code>Applet</code>.
     * 
     */
    public void checaColision() {
        if(basBase.getX() < 0) {
            basBase.setX(0);
            iDirBase = 0;
        }
        else if((basBase.getX() + basBase.getAncho()) > getWidth()) {
            basBase.setX(getWidth() - basBase.getAncho());
            iDirBase = 0;
        }
        
        
        if((basBola.getY() + basBola.getAlto()) >= getHeight()) {
            iDirBola = 0;
            bSpace = false;
            iVidasPlayer = iVidasPlayer - 1;
            basBola.setVelocidad(6);
            basBola.setVidas(1);
            basBase.setImagen(imaBase);
            basBola.setImagen(imaBola);
        }
        else if(basBola.getX() <= 0) {
            if(iDirBola == 2) {
                iDirBola = 1;
            }
            else if (iDirBola == 4) {
                iDirBola = 3;
            }
        }
        else if((basBola.getX() + basBola.getAncho()) >= getWidth()) {
            if(iDirBola == 1) {
                iDirBola = 2;
            }
            else if(iDirBola == 3) {
                iDirBola = 4;
            }
        }
        else if(basBola.getY() <= 20) {
            if(iDirBola == 1) {
                iDirBola = 3;
            }
            else if(iDirBola == 2) {
                iDirBola = 4;
            }
        }
        
        
        if(basBase.colisiona(basBola)){
            if(basBase.getY() > (basBola.getY() + basBola.getAlto())) {
                if(iDirBola == 3) {
                    iDirBola = 4;
                }
                else if(iDirBola == 4) {
                    iDirBola = 3;
                }
            }
            
            else if((basBola.getY() + basBola.getAlto()) >= basBase.getY()) {
                if(iDirBola == 3) {
                    iDirBola = 1;
                }
                
                else if(iDirBola == 4) {
                    iDirBola = 2;
                }
            }
        }
        
        for(Object objHero : lnkHero) {
            basBrickHero = (Base) objHero;
            
            if((basBola.getY() > (basBrickHero.getY() +
                    (basBrickHero.getAlto() / 2))) && 
                    (basBola.getY() < (basBrickHero.getY() +
                    (basBrickHero.getAlto())))){
                if(basBola.colisiona(basBrickHero)){
                    if(iDirBola == 1) {
                        iDirBola = 3;
                    }
                    else if(iDirBola == 2) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickHero.setVidas(basBrickHero.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickHero.getVidas() < 3) {
                        basBrickHero.setImagen(imaBrickHeroHalf);
                    }
                    if(basBrickHero.getVidas() <= 0) {
                        basBrickHero.setX(1000);
                        basBrickHero.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getY() + basBola.getAlto()) > 
                    basBrickHero.getY()) && 
                    ((basBola.getY() + basBola.getAlto()) <
                    (basBrickHero.getY() + (basBrickHero.getAlto() / 2)))) {
                if(basBola.colisiona(basBrickHero)){
                    if(iDirBola == 3) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 2;
                    }
                    else {
                        iDirBola = 1;
                    }
                    basBrickHero.setVidas(basBrickHero.getVidas() -
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickHero.getVidas() < 3) {
                        basBrickHero.setImagen(imaBrickHeroHalf);
                    }
                    if(basBrickHero.getVidas() <= 0) {
                        basBrickHero.setX(1000);
                        basBrickHero.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if((basBola.getX() > (basBrickHero.getX() +
                    (basBrickHero.getAncho() / 2))) &&
                    (basBola.getX() < (basBrickHero.getX() + 
                    basBrickHero.getAncho()))) {
                if(basBola.colisiona(basBrickHero)){
                    if(iDirBola == 2) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 3;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickHero.setVidas(basBrickHero.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickHero.getVidas() < 3) {
                        basBrickHero.setImagen(imaBrickHeroHalf);
                    }
                    if(basBrickHero.getVidas() <= 0) {
                        basBrickHero.setX(1000);
                        basBrickHero.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getX() + basBola.getAncho()) < 
                    ((basBrickHero.getX() + (basBrickHero.getAncho() / 2))) &&
                    ((basBola.getX() + basBola.getAncho()) > 
                    basBrickHero.getX()))) {
                if(basBola.colisiona(basBrickHero)){
                    if(iDirBola == 1) {
                        iDirBola = 2;
                    }
                    else if(iDirBola == 3) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 4;
                    }
                    basBrickHero.setVidas(basBrickHero.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickHero.getVidas() < 3) {
                        basBrickHero.setImagen(imaBrickHeroHalf);
                    }
                    if(basBrickHero.getVidas() <= 0) {
                        basBrickHero.setX(1000);
                        basBrickHero.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
        }
        
        for(Object objCoca : lnkCoca) {
            basBrickCoca = (Base) objCoca;
            
            if((basBola.getY() > (basBrickCoca.getY() +
                    (basBrickCoca.getAlto() / 2))) && 
                    (basBola.getY() < (basBrickCoca.getY() +
                    (basBrickCoca.getAlto())))){
                if(basBola.colisiona(basBrickCoca)){
                    if(iDirBola == 1) {
                        iDirBola = 3;
                    }
                    else if(iDirBola == 2) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickCoca.setVidas(basBrickCoca.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickCoca.getVidas() < 4) {
                        basBrickCoca.setImagen(imaBrickCocaHalf);
                    }
                    if(basBrickCoca.getVidas() <= 0) {
                        basBrickCoca.setX(1000);
                        basBrickCoca.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getY() + basBola.getAlto()) > 
                    basBrickCoca.getY()) && 
                    ((basBola.getY() + basBola.getAlto()) <
                    (basBrickCoca.getY() + (basBrickCoca.getAlto() / 2)))) {
                if(basBola.colisiona(basBrickCoca)){
                    if(iDirBola == 3) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 2;
                    }
                    else {
                        iDirBola = 1;
                    }
                    basBrickCoca.setVidas(basBrickCoca.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickCoca.getVidas() < 4) {
                        basBrickCoca.setImagen(imaBrickCocaHalf);
                    }
                    if(basBrickCoca.getVidas() <= 0) {
                        basBrickCoca.setX(1000);
                        basBrickCoca.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if((basBola.getX() > (basBrickCoca.getX() +
                    (basBrickCoca.getAncho() / 2))) &&
                    (basBola.getX() < (basBrickCoca.getX() + 
                    basBrickCoca.getAncho()))) {
                if(basBola.colisiona(basBrickCoca)){
                    if(iDirBola == 2) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 3;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickCoca.setVidas(basBrickCoca.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickCoca.getVidas() < 4) {
                        basBrickCoca.setImagen(imaBrickCocaHalf);
                    }
                    if(basBrickCoca.getVidas() <= 0) {
                        basBrickCoca.setX(1000);
                        basBrickCoca.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getX() + basBola.getAncho()) < 
                    ((basBrickCoca.getX() + (basBrickCoca.getAncho() / 2))) &&
                    ((basBola.getX() + basBola.getAncho()) > 
                    basBrickCoca.getX()))) {
                if(basBola.colisiona(basBrickCoca)){
                    if(iDirBola == 1) {
                        iDirBola = 2;
                    }
                    else if(iDirBola == 3) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 4;
                    }
                    basBrickCoca.setVidas(basBrickCoca.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickCoca.getVidas() < 4) {
                        basBrickCoca.setImagen(imaBrickCocaHalf);
                    }
                    if(basBrickCoca.getVidas() <= 0) {
                        basBrickCoca.setX(1000);
                        basBrickCoca.setY(1000);
                        iScore = iScore + 100;
                        iBricks = iBricks + 1;
                    }
                }
            }
        }
        
        for(Object objXsis : lnkXsis) {
            basBrickXsis = (Base) objXsis;
            
            if((basBola.getY() > (basBrickXsis.getY() +
                    (basBrickXsis.getAlto() / 2))) && 
                    (basBola.getY() < (basBrickXsis.getY() +
                    (basBrickXsis.getAlto())))){
                if(basBola.colisiona(basBrickXsis)){
                    if(iDirBola == 1) {
                        iDirBola = 3;
                    }
                    else if(iDirBola == 2) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickXsis.setVidas(basBrickXsis.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickXsis.getVidas() < 2) {
                        basBrickXsis.setImagen(imaBrickXsisHalf);
                    }
                    if(basBrickXsis.getVidas() <= 0) {
                        basBrickXsis.setX(1000);
                        basBrickXsis.setY(1000);
                        iScore = iScore + 50;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getY() + basBola.getAlto()) > 
                    basBrickXsis.getY()) && 
                    ((basBola.getY() + basBola.getAlto()) <
                    (basBrickXsis.getY() + (basBrickXsis.getAlto() / 2)))) {
                if(basBola.colisiona(basBrickXsis)){
                    if(iDirBola == 3) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 2;
                    }
                    else {
                        iDirBola = 1;
                    }
                    basBrickXsis.setVidas(basBrickXsis.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickXsis.getVidas() < 2) {
                        basBrickXsis.setImagen(imaBrickXsisHalf);
                    }
                    if(basBrickXsis.getVidas() <= 0) {
                        basBrickXsis.setX(1000);
                        basBrickXsis.setY(1000);
                        iScore = iScore + 50;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if((basBola.getX() > (basBrickXsis.getX() +
                    (basBrickXsis.getAncho() / 2))) &&
                    (basBola.getX() < (basBrickXsis.getX() + 
                    basBrickXsis.getAncho()))) {
                if(basBola.colisiona(basBrickXsis)){
                    if(iDirBola == 2) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 3;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickXsis.setVidas(basBrickXsis.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickXsis.getVidas() < 2) {
                        basBrickXsis.setImagen(imaBrickXsisHalf);
                    }
                    if(basBrickXsis.getVidas() <= 0) {
                        basBrickXsis.setX(1000);
                        basBrickXsis.setY(1000);
                        iScore = iScore + 50;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getX() + basBola.getAncho()) < 
                    ((basBrickXsis.getX() + (basBrickXsis.getAncho() / 2))) &&
                    ((basBola.getX() + basBola.getAncho()) > 
                    basBrickXsis.getX()))) {
                if(basBola.colisiona(basBrickXsis)){
                    if(iDirBola == 1) {
                        iDirBola = 2;
                    }
                    else if(iDirBola == 3) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 4;
                    }
                    basBrickXsis.setVidas(basBrickXsis.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickXsis.getVidas() < 2) {
                        basBrickXsis.setImagen(imaBrickXsisHalf);
                    }
                    if(basBrickXsis.getVidas() <= 0) {
                        basBrickXsis.setX(1000);
                        basBrickXsis.setY(1000);
                        iScore = iScore + 50;
                        iBricks = iBricks + 1;
                    }
                }
            }
        }
        
        for(Object objMeth : lnkMeth) {
            basBrickMeth = (Base) objMeth;
            
            if((basBola.getY() > (basBrickMeth.getY() +
                    (basBrickMeth.getAlto() / 2))) && 
                    (basBola.getY() < (basBrickMeth.getY() +
                    (basBrickMeth.getAlto())))){
                if(basBola.colisiona(basBrickMeth)){
                    if(iDirBola == 1) {
                        iDirBola = 3;
                    }
                    else if(iDirBola == 2) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickMeth.setVidas(basBrickMeth.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickMeth.getVidas() < 5) {
                        basBrickMeth.setImagen(imaBrickMethHalf);
                    }
                    if(basBrickMeth.getVidas() <= 0) {
                        basBrickMeth.setX(1000);
                        basBrickMeth.setY(1000);
                        iScore = iScore + 200;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getY() + basBola.getAlto()) > 
                    basBrickMeth.getY()) && 
                    ((basBola.getY() + basBola.getAlto()) <
                    (basBrickMeth.getY() + (basBrickMeth.getAlto() / 2)))) {
                if(basBola.colisiona(basBrickMeth)){
                    if(iDirBola == 3) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 2;
                    }
                    else {
                        iDirBola = 1;
                    }
                    basBrickMeth.setVidas(basBrickMeth.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickMeth.getVidas() < 5) {
                        basBrickMeth.setImagen(imaBrickMethHalf);
                    }
                    if(basBrickMeth.getVidas() <= 0) {
                        basBrickMeth.setX(1000);
                        basBrickMeth.setY(1000);
                        iScore = iScore + 200;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if((basBola.getX() > (basBrickMeth.getX() +
                    (basBrickMeth.getAncho() / 2))) &&
                    (basBola.getX() < (basBrickMeth.getX() + 
                    basBrickMeth.getAncho()))) {
                if(basBola.colisiona(basBrickMeth)){
                    if(iDirBola == 2) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 3;
                    }
                    else {
                        iDirBola = 3;
                    }
                    basBrickMeth.setVidas(basBrickMeth.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickMeth.getVidas() < 5) {
                        basBrickMeth.setImagen(imaBrickMethHalf);
                    }
                    if(basBrickMeth.getVidas() <= 0) {
                        basBrickMeth.setX(1000);
                        basBrickMeth.setY(1000);
                        iScore = iScore + 200;
                        iBricks = iBricks + 1;
                    }
                }
            }
            else if(((basBola.getX() + basBola.getAncho()) < 
                    ((basBrickMeth.getX() + (basBrickMeth.getAncho() / 2))) &&
                    ((basBola.getX() + basBola.getAncho()) > 
                    basBrickMeth.getX()))) {
                if(basBola.colisiona(basBrickMeth)){
                    if(iDirBola == 1) {
                        iDirBola = 2;
                    }
                    else if(iDirBola == 3) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 4;
                    }
                    basBrickMeth.setVidas(basBrickMeth.getVidas() - 
                            basBola.getVidas());
                    souBrickCrash.play();
                    if(basBrickMeth.getVidas() < 5) {
                        basBrickMeth.setImagen(imaBrickMethHalf);
                    }
                    if(basBrickMeth.getVidas() <= 0) {
                        basBrickMeth.setX(1000);
                        basBrickMeth.setY(1000);
                        iScore = iScore + 200;
                        iBricks = iBricks + 1;
                    }
                }
            }
        }
        
        for(Object objEspecial : lnkEspecial) {
            basBrickEspecial = (Base) objEspecial;
            
            if((basBola.getY() > (basBrickEspecial.getY() +
                    (basBrickEspecial.getAlto() / 2))) && 
                    (basBola.getY() < (basBrickEspecial.getY() +
                    (basBrickEspecial.getAlto())))){
                if(basBola.colisiona(basBrickEspecial)){
                    if(iDirBola == 1) {
                        iDirBola = 3;
                    }
                    else if(iDirBola == 2) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 3;
                    }
                    if(!bEspecial) {
                        basBrickEspecial.setVidas(basBrickEspecial.getVidas() - 
                                1);
                    }
                    souBrickCrash.play();
                }
            }
            else if(((basBola.getY() + basBola.getAlto()) > 
                    basBrickEspecial.getY()) && 
                    ((basBola.getY() + basBola.getAlto()) <
                    (basBrickEspecial.getY() + 
                    (basBrickEspecial.getAlto() / 2)))) {
                if(basBola.colisiona(basBrickEspecial)){
                    if(iDirBola == 3) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 2;
                    }
                    else {
                        iDirBola = 1;
                    }
                    if(!bEspecial) {
                        basBrickEspecial.setVidas(basBrickEspecial.getVidas() - 
                                1);
                    }
                    souBrickCrash.play();
                }
            }
            else if((basBola.getX() > (basBrickEspecial.getX() +
                    (basBrickEspecial.getAncho() / 2))) &&
                    (basBola.getX() < (basBrickEspecial.getX() + 
                    basBrickEspecial.getAncho()))) {
                if(basBola.colisiona(basBrickEspecial)){
                    if(iDirBola == 2) {
                        iDirBola = 1;
                    }
                    else if(iDirBola == 4) {
                        iDirBola = 3;
                    }
                    else {
                        iDirBola = 3;
                    }
                    if(!bEspecial) {
                        basBrickEspecial.setVidas(basBrickEspecial.getVidas() - 
                                1);
                    }
                    souBrickCrash.play();
                }
            }
            else if(((basBola.getX() + basBola.getAncho()) < 
                    ((basBrickEspecial.getX() + 
                    (basBrickEspecial.getAncho() / 2))) &&
                    ((basBola.getX() + basBola.getAncho()) > 
                    basBrickEspecial.getX()))) {
                if(basBola.colisiona(basBrickEspecial)){
                    if(iDirBola == 1) {
                        iDirBola = 2;
                    }
                    else if(iDirBola == 3) {
                        iDirBola = 4;
                    }
                    else {
                        iDirBola = 4;
                    }
                    if(!bEspecial) {
                        basBrickEspecial.setVidas(basBrickEspecial.getVidas() - 
                                1);
                    }
                    souBrickCrash.play();
                }
            }
            
            if(basBrickEspecial.getVidas() <= 0) {
                if(basBrickEspecial.getVelocidad() < 4) {
                    iRan = (int) (Math.random() * 6);
                    switch(iRan) {
                        case 0:
                            basEspMenosVida.setX(basBrickEspecial.getX());
                            basEspMenosVida.setY(basBrickEspecial.getY());
                            bEspecial = true;
                            break;
                        case 1:
                            basEspMasVida.setX(basBrickEspecial.getX());
                            basEspMasVida.setY(basBrickEspecial.getY());
                            bEspecial = true;
                            break;
                        case 2:
                            basEspMenosBarra.setX(basBrickEspecial.getX());
                            basEspMenosBarra.setY(basBrickEspecial.getY());
                            bEspecial = true;
                            break;
                        case 3:
                            basEspMasBarra.setX(basBrickEspecial.getX());
                            basEspMasBarra.setY(basBrickEspecial.getY());
                            bEspecial = true;
                            break;
                        case 4:
                            basEspX2Vel.setX(basBrickEspecial.getX());
                            basEspX2Vel.setY(basBrickEspecial.getY());
                            bEspecial = true;
                            break;
                        case 5:
                        case 6:
                            basEspX2Power.setX(basBrickEspecial.getX());
                            basEspX2Power.setY(basBrickEspecial.getY());
                            bEspecial = true;
                            break;
                    }
                    basBrickEspecial.setX(1000);
                    basBrickEspecial.setY(1000);
                    basBrickEspecial.setVelocidad(5);
                }
            }
        }
        
        if(bEspecial) {
            switch(iRan) {
                case 0:
                    if(basBase.colisiona(basEspMenosVida)) {
                        basEspMenosVida.setX(1000);
                        bEspecial = false;
                        iVidasPlayer = iVidasPlayer - 1;
                    }
                    else if((basEspMenosVida.getY() + 
                            basEspMenosVida.getAlto()) >= getHeight()) {
                        basEspMenosVida.setX(1000);
                        bEspecial = false;
                    }
                    break;
                case 1:
                    if(basBase.colisiona(basEspMasVida)) {
                        basEspMasVida.setX(1000);
                        bEspecial = false;
                        iVidasPlayer = iVidasPlayer + 1;
                    }
                    else if((basEspMasVida.getY() + 
                            basEspMasVida.getAlto()) >= getHeight()) {
                        basEspMasVida.setX(1000);
                        bEspecial = false;
                    }
                    break;
                case 2:
                    if(basBase.colisiona(basEspMenosBarra)) {
                        basEspMenosBarra.setX(1000);
                        bEspecial = false;
                        basBase.setImagen(imaBaseChica);
                    }
                    else if((basEspMenosBarra.getY() + 
                            basEspMenosBarra.getAlto()) >= getHeight()) {
                        basEspMenosBarra.setX(1000);
                        bEspecial = false;
                    }
                    break;
                case 3:
                    if(basBase.colisiona(basEspMasBarra)) {
                        basEspMasBarra.setX(1000);
                        bEspecial = false;
                        basBase.setImagen(imaBaseGrande);
                    }
                    else if((basEspMasBarra.getY() + 
                            basEspMasBarra.getAlto()) >= getHeight()) {
                        basEspMasBarra.setX(1000);
                        bEspecial = false;
                    }
                    break;
                case 4:
                    if(basBase.colisiona(basEspX2Vel)) {
                        basEspX2Vel.setX(1000);
                        bEspecial = false;
                        basBola.setVelocidad(basBola.getVelocidad() * 2);
                    }
                    else if((basEspX2Vel.getY() + 
                            basEspX2Vel.getAlto()) >= getHeight()) {
                        basEspX2Vel.setX(1000);
                        bEspecial = false;
                    }
                    break;
                case 5:
                case 6:
                    if(basBase.colisiona(basEspX2Power)) {
                        basEspX2Power.setX(1000);
                        bEspecial = false;
                        basBola.setVidas(basBola.getVidas() * 2);
                        basBola.setImagen(imaBolaFuego);
                    }
                    else if((basEspX2Power.getY() + 
                            basEspX2Power.getAlto()) >= getHeight()) {
                        basEspX2Power.setX(1000);
                        bEspecial = false;
                    }
                    break;
            }
        }
    }
    
    
    /**
     * levelUp
     * 
     * Metodo utilizado para restaurar los valores originales de los objetos
     * para un nuevo nivel
     * 
     */
    public void levelUp() {

        iDirBola = 0;
        iDirBase = 0;
        iVelBase = 10;
        iRan = 0;
        bPausa = true;
        bSoltarBola = false;
        bGameStart = false;
        bSpace = false;
        bEspecial = false;
        bEndGame = false;
        
        souMusica.stop();
        souIntro.stop();
        souGameStart.play();
        
        basBase.setImagen(imaBase);
        basBase.setX(((getWidth() / 2) - (basBase.getAncho() / 2)));
        basBase.setY(getHeight() - basBase.getAlto());
        
        basBola.setImagen(imaBola);
        basBola.setVelocidad(6);
        basBola.setVidas(1);
        basBola.setX((basBase.getX() + (basBase.getAncho() / 2)) -
                (basBola.getAncho() / 2));
        basBola.setY(basBase.getY() - basBola.getAlto() - 1);
        
        
        lnkEspecial.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickEspecial = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickespecial.png"));
            // Creo a BrickCoca1
            basBrickEspecial = new Base(0, 0, imaBrickEspecial, iVidasEspecial);
            basBrickEspecial.setVidas(1);
            basBrickEspecial.setVelocidad(1);
            if(iI == 0) {
                basBrickEspecial.setX(450);
                basBrickEspecial.setY(100);
            }
            else if(iI == 1) {
                basBrickEspecial.setX(350);
                basBrickEspecial.setY(150);
            }
            else if(iI == 2) {
                basBrickEspecial.setX(250);
                basBrickEspecial.setY(200);
            }
            else if(iI == 3) {
                basBrickEspecial.setX(150);
                basBrickEspecial.setY(250);
            }
            lnkEspecial.add(basBrickEspecial);
        }
        
        lnkCoca.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickCoca = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickcocaina.png"));
            // Creo a BrickCoca1
            basBrickCoca = new Base(0, 0, imaBrickCoca, iVidasCocaina);
            basBrickCoca.setVidas(6);
            if(iI == 0) {
                basBrickCoca.setX(50);
                basBrickCoca.setY(250);
            }
            else if(iI == 1) {
                basBrickCoca.setX(250);
                basBrickCoca.setY(250);
            }
            else if(iI == 2) {
                basBrickCoca.setX(350);
                basBrickCoca.setY(250);
            }
            else if(iI == 3) {
                basBrickCoca.setX(450);
                basBrickCoca.setY(250);
            }
            lnkCoca.add(basBrickCoca);
        }
        
        lnkHero.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickHero = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickheroina.png"));
            // Creo a BrickCoca1
            basBrickHero = new Base(0, 0, imaBrickHero, iVidasHeroina);
            basBrickHero.setVidas(4);
            if(iI == 0) {
                basBrickHero.setX(50);
                basBrickHero.setY(100);
            }
            else if(iI == 1) {
                basBrickHero.setX(150);
                basBrickHero.setY(100);
            }
            else if(iI == 2) {
                basBrickHero.setX(250);
                basBrickHero.setY(100);
            }
            else if(iI == 3) {
                basBrickHero.setX(350);
                basBrickHero.setY(100);
            }
            lnkHero.add(basBrickHero);
        }
        
        lnkXsis.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickXsis = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickxtasis.png"));
            // Creo a BrickCoca1
            basBrickXsis = new Base(0, 0, imaBrickXsis, iVidasXtasis);
            basBrickXsis.setVidas(2);
            if(iI == 0) {
                basBrickXsis.setX(50);
                basBrickXsis.setY(150);
            }
            else if(iI == 1) {
                basBrickXsis.setX(150);
                basBrickXsis.setY(150);
            }
            else if(iI == 2) {
                basBrickXsis.setX(250);
                basBrickXsis.setY(150);
            }
            else if(iI == 3) {
                basBrickXsis.setX(450);
                basBrickXsis.setY(150);
            }
            lnkXsis.add(basBrickXsis);
        }
        
        lnkMeth.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickMeth = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickmeth.png"));
            // Creo a BrickCoca1
            basBrickMeth = new Base(0, 0, imaBrickMeth, iVidasMeth);
            basBrickMeth.setVidas(8);
            if(iI == 0) {
                basBrickMeth.setX(50);
                basBrickMeth.setY(200);
            }
            else if(iI == 1) {
                basBrickMeth.setX(150);
                basBrickMeth.setY(200);
            }
            else if(iI == 2) {
                basBrickMeth.setX(350);
                basBrickMeth.setY(200);
            }
            else if(iI == 3) {
                basBrickMeth.setX(450);
                basBrickMeth.setY(200);
            }
            lnkMeth.add(basBrickMeth);
        }
    }
    
    /**
     * reset
     * 
     * Metodo usado para resetear los valores una vez que se acaba el juego
     * y volver a jugar
     * 
     */
    public void reset() {
        iVidasPlayer = 5;
        iLevel = 1;
        iScore = 0;
        iBricks = 0;
        iDirBola = 0;
        iDirBase = 0;
        iVelBase = 10;
        iRan = 0;
        bPausa = false;
        bSoltarBola = false;
        bGameStart = true;
        bSpace = false;
        bEspecial = false;
        bEndGame = false;
        bInstrucciones = false;
        
        souMusica.stop();
        souIntro.stop();
        souGameStart.stop();
        souEndGame.stop();
        souMusica.play();
        
        basBase.setImagen(imaBase);
        basBase.setX(((getWidth() / 2) - (basBase.getAncho() / 2)));
        basBase.setY(getHeight() - basBase.getAlto());
        
        basBola.setImagen(imaBola);
        basBola.setVelocidad(6);
        basBola.setVidas(1);
        basBola.setX((basBase.getX() + (basBase.getAncho() / 2)) -
                (basBola.getAncho() / 2));
        basBola.setY(basBase.getY() - basBola.getAlto() - 1);
        
        
        lnkEspecial.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickEspecial = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickespecial.png"));
            // Creo a BrickCoca1
            basBrickEspecial = new Base(0, 0, imaBrickEspecial, iVidasEspecial);
            basBrickEspecial.setVidas(1);
            basBrickEspecial.setVelocidad(1);
            if(iI == 0) {
                basBrickEspecial.setX(450);
                basBrickEspecial.setY(100);
            }
            else if(iI == 1) {
                basBrickEspecial.setX(350);
                basBrickEspecial.setY(150);
            }
            else if(iI == 2) {
                basBrickEspecial.setX(250);
                basBrickEspecial.setY(200);
            }
            else if(iI == 3) {
                basBrickEspecial.setX(150);
                basBrickEspecial.setY(250);
            }
            lnkEspecial.add(basBrickEspecial);
        }
        
        lnkCoca.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickCoca = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickcocaina.png"));
            // Creo a BrickCoca1
            basBrickCoca = new Base(0, 0, imaBrickCoca, iVidasCocaina);
            basBrickCoca.setVidas(6);
            if(iI == 0) {
                basBrickCoca.setX(50);
                basBrickCoca.setY(250);
            }
            else if(iI == 1) {
                basBrickCoca.setX(250);
                basBrickCoca.setY(250);
            }
            else if(iI == 2) {
                basBrickCoca.setX(350);
                basBrickCoca.setY(250);
            }
            else if(iI == 3) {
                basBrickCoca.setX(450);
                basBrickCoca.setY(250);
            }
            lnkCoca.add(basBrickCoca);
        }
        
        lnkHero.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickHero = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickheroina.png"));
            // Creo a BrickCoca1
            basBrickHero = new Base(0, 0, imaBrickHero, iVidasHeroina);
            basBrickHero.setVidas(4);
            if(iI == 0) {
                basBrickHero.setX(50);
                basBrickHero.setY(100);
            }
            else if(iI == 1) {
                basBrickHero.setX(150);
                basBrickHero.setY(100);
            }
            else if(iI == 2) {
                basBrickHero.setX(250);
                basBrickHero.setY(100);
            }
            else if(iI == 3) {
                basBrickHero.setX(350);
                basBrickHero.setY(100);
            }
            lnkHero.add(basBrickHero);
        }
        
        lnkXsis.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickXsis = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickxtasis.png"));
            // Creo a BrickCoca1
            basBrickXsis = new Base(0, 0, imaBrickXsis, iVidasXtasis);
            basBrickXsis.setVidas(2);
            if(iI == 0) {
                basBrickXsis.setX(50);
                basBrickXsis.setY(150);
            }
            else if(iI == 1) {
                basBrickXsis.setX(150);
                basBrickXsis.setY(150);
            }
            else if(iI == 2) {
                basBrickXsis.setX(250);
                basBrickXsis.setY(150);
            }
            else if(iI == 3) {
                basBrickXsis.setX(450);
                basBrickXsis.setY(150);
            }
            lnkXsis.add(basBrickXsis);
        }
        
        lnkMeth.clear();
        for(int iI = 0; iI < 4; iI++){
            // se crea imagen de BrickCoca1
            imaBrickMeth = Toolkit.getDefaultToolkit().
                    getImage(this.getClass().getResource("brickmeth.png"));
            // Creo a BrickCoca1
            basBrickMeth = new Base(0, 0, imaBrickMeth, iVidasMeth);
            basBrickMeth.setVidas(8);
            if(iI == 0) {
                basBrickMeth.setX(50);
                basBrickMeth.setY(200);
            }
            else if(iI == 1) {
                basBrickMeth.setX(150);
                basBrickMeth.setY(200);
            }
            else if(iI == 2) {
                basBrickMeth.setX(350);
                basBrickMeth.setY(200);
            }
            else if(iI == 3) {
                basBrickMeth.setX(450);
                basBrickMeth.setY(200);
            }
            lnkMeth.add(basBrickMeth);
        }
    }
    
    
    /**
     * update
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor y 
     * define cuando usar ahora el paint
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     * 
     */
    public void paint (Graphics graGrafico) {
        // Inicializan el DoubleBuffer
        if (imaImagenApplet == null) {
                imaImagenApplet = createImage (this.getSize().width, 
                        this.getSize().height);
                graGraficaApplet = imaImagenApplet.getGraphics ();
        }

        if ((iVidasPlayer > 0) && (iLevel < 4)) {
            // creo imagen para el background pero es un error por que el fondo 
            //esta en imagenraton
            URL urlImagenFondo = this.getClass().getResource("fondo_BrBa.jpg");
            Image imaImagenEspacio = 
                    Toolkit.getDefaultToolkit().getImage(urlImagenFondo);

            // Despliego la imagen
            graGraficaApplet.drawImage(imaImagenEspacio, 0, 0, 
                    getWidth(), getHeight(), this);
        }
        //Si iVidas es igual a 0, mostrar imagen de Game Over
        else if(iVidasPlayer <= 0) {
            // creo imagen para el background pero es un error por que el fondo 
            //esta en imagenraton
            URL urlImagenFondo = this.getClass().getResource("PERDISTE.png");
            Image imaImagenEspacio = 
                    Toolkit.getDefaultToolkit().getImage(urlImagenFondo);

            // Despliego la imagen
            graGraficaApplet.drawImage(imaImagenEspacio, 0, 0, 
                    getWidth(), getHeight(), this);
        }
        else if(iLevel > 3) {
            // creo imagen para Ganador
            URL urlImagenFondo = 
                    this.getClass().getResource("GANASTE.png");
            Image imaImagenEspacio = 
                    Toolkit.getDefaultToolkit().getImage(urlImagenFondo);

            // Despliego la imagen
            graGraficaApplet.drawImage(imaImagenEspacio, 0, 0, 
                    getWidth(), getHeight(), this);
        }
        if((bInstrucciones) && (iVidasPlayer > 0)){
            // creo imagen para el frente de instrucciones
            URL urlImagenFondo = 
                    this.getClass().getResource("INSTRUCCIONES.png");
            Image imaImagenEspacio = 
                    Toolkit.getDefaultToolkit().getImage(urlImagenFondo);

            // Despliego la imagen
            graGraficaApplet.drawImage(imaImagenEspacio, 0, 0, 
                    getWidth(), getHeight(), this);
        }
        

        // Actualiza el Foreground.
        graGraficaApplet.setColor (getForeground());
        paint1(graGraficaApplet);

        // Dibuja la imagen actualizada
        graGrafico.drawImage (imaImagenApplet, 0, 0, this);
    }
    
    /**
     * paint
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada,
     * ademas que cuando la imagen es cargada te despliega una advertencia.
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     * 
     */
    public void paint1(Graphics g) {
        if((iVidasPlayer > 0) && (!bInstrucciones) && (iLevel < 4)) {
            //Si la imagen ya se cargo
            if (basBase != null && basBola != null && basBrickCoca != null && 
                    basBrickHero != null && basBrickXsis != null && 
                    basBrickMeth != null) {                                         //especificar mas
                //Dibuja la imagen de la Base en la posicion actualizada
                g.drawImage(basBase.getImagen(), basBase.getX(),
                        basBase.getY(), this);

                //Dibuja la imagen de la Bola en la posicion actualizada
                g.drawImage(basBola.getImagen(), basBola.getX(),
                        basBola.getY(), this);
                
                g.drawImage(basEspMenosVida.getImagen(), basEspMenosVida.getX(),
                        basEspMenosVida.getY(), this);
                
                g.drawImage(basEspMasVida.getImagen(), basEspMasVida.getX(),
                        basEspMasVida.getY(), this);
                
                g.drawImage(basEspMenosBarra.getImagen(), 
                        basEspMenosBarra.getX(), basEspMenosBarra.getY(), this);
                
                g.drawImage(basEspMasBarra.getImagen(), basEspMasBarra.getX(),
                        basEspMasBarra.getY(), this);
                
                g.drawImage(basEspX2Vel.getImagen(), basEspX2Vel.getX(),
                        basEspX2Vel.getY(), this);
                
                g.drawImage(basEspX2Power.getImagen(), basEspX2Power.getX(),
                        basEspX2Power.getY(), this);
                
            
                for(Object objBrickCoca : lnkCoca) {
                    basBrickCoca = (Base) objBrickCoca;
                    //Dibuja la imagen de la BrickCoca en la posicion 
                    //actualizada
                    if ((basBrickCoca.getVidas() < 1) && 
                            (basBrickCoca.getVidas() > - 5)) {
                        switch(basBrickCoca.getiNumLista()){
                            case 0:
                                g.drawImage(aniCoca.getImagen(), 
                                        50, 250, this);
                                break;
                            case 1:
                                g.drawImage(aniCoca.getImagen(), 
                                        250, 250, this);
                                break;
                            case 2:
                                g.drawImage(aniCoca.getImagen(), 
                                        350, 250, this);
                                break;
                            case 3:
                                g.drawImage(aniCoca.getImagen(), 
                                        450, 250, this);
                                break;
                        }
                        basBrickCoca.setVidas(basBrickCoca.getVidas() - 1);
                    }
                    g.drawImage(basBrickCoca.getImagen(), basBrickCoca.getX(),
                            basBrickCoca.getY(), this);
                }
            
                for(Object objBrickHero : lnkHero) {
                    basBrickHero = (Base) objBrickHero;
                    //Dibuja la imagen de la BrickHero en la posicion 
                    //actualizada
                    if ((basBrickHero.getVidas() < 1) && 
                            (basBrickHero.getVidas() > - 5)) {
                        switch(basBrickHero.getiNumLista()){
                            case 0:
                                g.drawImage(aniHero.getImagen(), 50, 100, this);
                                break;
                            case 1:
                                g.drawImage(aniHero.getImagen(), 150, 100, this);
                                break;
                            case 2:
                                g.drawImage(aniHero.getImagen(), 250, 100, this);
                                break;
                            case 3:
                                g.drawImage(aniHero.getImagen(), 350, 100, this);
                                break;
                        }
                        basBrickHero.setVidas(basBrickHero.getVidas() - 1);
                    }
                    g.drawImage(basBrickHero.getImagen(), basBrickHero.getX(),
                            basBrickHero.getY(), this);
                }
            
                for(Object objBrickXsis : lnkXsis) {
                    basBrickXsis = (Base) objBrickXsis;
                    //Dibuja la imagen de la BrickXsis en la posicion 
                    //actualizada
                    if ((basBrickXsis.getVidas() < 1) && 
                            (basBrickXsis.getVidas() > - 5)) {
                        switch(basBrickXsis.getiNumLista()){
                            case 0:
                                g.drawImage(aniXsis.getImagen(), 
                                        50, 150, this);
                                break;
                            case 1:
                                g.drawImage(aniXsis.getImagen(), 
                                        150, 150, this);
                                break;
                            case 2:
                                g.drawImage(aniXsis.getImagen(), 
                                        250, 150, this);
                                break;
                            case 3:
                                g.drawImage(aniXsis.getImagen(), 
                                        450, 150, this);
                                break;
                        }
                        basBrickXsis.setVidas(basBrickXsis.getVidas() - 1);
                    }
                    g.drawImage(basBrickXsis.getImagen(), basBrickXsis.getX(),
                            basBrickXsis.getY(), this);
                }
            
                for(Object objBrickMeth : lnkMeth) {
                    basBrickMeth = (Base) objBrickMeth;
                    //Dibuja la imagen de la BrickMeth en la posicion 
                    //actualizada
                    if ((basBrickMeth.getVidas() < 1) && 
                            (basBrickMeth.getVidas() > - 5)) {
                        switch(basBrickMeth.getiNumLista()){
                            case 0:
                                g.drawImage(aniMeth.getImagen(), 50, 200, this);
                                break;
                            case 1:
                                g.drawImage(aniMeth.getImagen(), 150, 200, this);
                                break;
                            case 2:
                                g.drawImage(aniMeth.getImagen(), 350, 200, this);
                                break;
                            case 3:
                                g.drawImage(aniMeth.getImagen(), 450, 200, this);
                                break;
                        }
                        basBrickMeth.setVidas(basBrickMeth.getVidas() - 1);
                    }
                    g.drawImage(basBrickMeth.getImagen(), basBrickMeth.getX(),
                            basBrickMeth.getY(), this);
                }
                
                for(Object objBrickEspecial : lnkEspecial) {
                    basBrickEspecial = (Base) objBrickEspecial;
                    //Dibuja la imagen de la BrickEspecial en la posicion 
                    //actualizada
                    g.drawImage(basBrickEspecial.getImagen(),
                            basBrickEspecial.getX(), 
                            basBrickEspecial.getY(), this);
                }
            }
        
            else {
                //Da un mensaje mientras se carga el dibujo	
                g.drawString("No se cargo la imagen..", 20, 550);
            }
            
            g.setColor(Color.white);
            g.setFont(new Font("Verdana", Font.PLAIN, 20));
            // dibuja el score
            g.drawString("Score: " + iScore + "  Vidas: " + iVidasPlayer +
                    " Level: " + iLevel, 20, 40);
        }
        else if((iVidasPlayer <= 0) || (iLevel > 3)) {
            g.setColor(Color.white);
            g.setFont(new Font("Verdana", Font.PLAIN, 30));
            g.drawString("Fin de juego", 210, 435);
            g.drawString("Score: " + iScore, 230, 570);
            g.setFont(new Font("Verdana", Font.PLAIN, 20));
            g.drawString("Presiona 'S' para volver a jugar", 120, 590);
        }
        
    }
    
    
    /**
     * KeyTyped
     * 
     * @param e 
     */
    public void keyTyped(KeyEvent e) {
        //se tiene que escribir el metodo
    }

    /**
     * KeyPressed
     * 
     * Metodo que controla posicion de Base de acuerdo a las teclas
     * 
     * @param e 
     */
    public void keyPressed(KeyEvent keyPress) {
        if (keyPress.getKeyCode() == KeyEvent.VK_RIGHT) {
            iDirBase = 1;
        }
        else if (keyPress.getKeyCode() == KeyEvent.VK_LEFT) {
            iDirBase = 2;
        }
    }

    /**
     * KeyReleased
     * 
     * Metodo que controla posicion de Base, pausa e instrucciones
     * de acuerdo a las teclas
     * 
     * @param e 
     */
    public void keyReleased(KeyEvent keyRel) {
        if (keyRel.getKeyCode() == KeyEvent.VK_RIGHT) {
            iDirBase = 0;
        }
        if (keyRel.getKeyCode() == KeyEvent.VK_LEFT) {
            iDirBase = 0;
        }
        if (keyRel.getKeyCode() == KeyEvent.VK_P) {
            bPausa = !bPausa;
        }
        if (keyRel.getKeyCode() == KeyEvent.VK_S) {
            if(bPausa) {
                bPausa = false;
            }
            if(!bGameStart) {
                bGameStart = true;
                souIntro.stop();
                souMusica.stop();
                souGameStart.stop();
                souMusica.play();
            }
            if((iVidasPlayer <= 0) || (iLevel > 3)) {
                reset();
            }
        }
        if (bGameStart){
            if (keyRel.getKeyCode() == KeyEvent.VK_SPACE) {
                bSoltarBola = true;
                if(!bSpace) {
                    iDirBola = 1;
                    bSpace = true;
                }
            }
        }
        if (keyRel.getKeyCode() == KeyEvent.VK_I) {
            bInstrucciones = !bInstrucciones;
            souIntro.stop();
            souMusica.stop();
            souGameStart.stop();
            if(!bGameStart) {
                souGameStart.play();
            }
            else {
                souMusica.play();
            }
        }
    }
    
}
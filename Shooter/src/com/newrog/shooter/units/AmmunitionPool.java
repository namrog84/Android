package com.newrog.shooter.units;

import com.badlogic.gdx.utils.Array;
import com.newrog.shooter.ShooterGame;

public class AmmunitionPool {

    public static Array<Ammunition> pool;

    private static ShooterGame game;

    public AmmunitionPool (ShooterGame game) {
        this.game = game;
    }

    public static void init () {
        pool = new Array<Ammunition>();

        // Lets start with 10 Bullets
        for (int i = 0; i < 2; ++i) {
            pool.add(new Bullet(game, 0, 0, 0));
        }
    }

    public static Bullet getBullet () {
        if (pool == null) {
            init();
        }

      //  System.out.println("beep");
        if (pool.size < 1) { //If there aren't any more, lets add some more!
            System.out.println("boom");
            for (int i = 0; i < 10; ++i) {
                pool.add(new Bullet(game, 0, 0, 0));
            }
        }
       // Bullet e = (Bullet)pool.get(0);
        
        
       // System.out.println(e);
        return (Bullet)pool.removeIndex(0);
    }
    
    public static void returnBullet(Bullet e) {
        pool.add(e);
    }

}

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.*;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.*;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.node.SceneObject;
import source.methods;
import source.skillinfo;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: RedJack
 */

@Manifest(authors = {"RedJack"}, name = "RedJack Jewelry", description = "Crafts almost all jewelry", version = 0.1)
public class RedJackJewelry extends ActiveScript implements PaintListener {

    Timer animationTimer = new Timer(1800);
    private final NumberFormat nf = NumberFormat.getInstance();

    public static Tile bankTile = new Tile(3097, 3496, 0), furnaceTile = new Tile(3109, 3501, 0);
    public static Area bankArea = new Area(new Tile(3094, 3495, 0), new Tile(3098, 3499, 0)),
            furnaceArea = new Area(new Tile(3106, 3497, 0), new Tile(3111, 3502, 0));

    public static int goldBar = 2357, gemId, furnaceID, intid;
    public static String loc, item, status = "Loading...";

    Image mouse;

    public static boolean ready = false;

    JGUI g;

    Player me() {
        return Players.getLocal();
    }

    SceneObject furnace() {

        return SceneEntities.getNearest(furnaceID);
    }

    boolean inArea(Area a) {
        return a.contains(me().getLocation());
    }

    public void onStart() {


        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JGUI g = new JGUI();
                    g.setVisible(true);
                }
            });
            while (g.isVisible()) {
                methods.sleep(150);
            }
        } catch (Exception e) {

        }

        try {
            mouse = ImageIO.read(new URL("https://raw.github.com/RedJackScripts/Powerbot-Scripts/master/mouse.png"));
        } catch (IOException e) {

        }

        skillinfo.startExp = Skills.getExperience(Skills.CRAFTING);
        skillinfo.startTime = System.currentTimeMillis();
    }

    @Override
    public int loop() {
        if (ready == true) {
            if (Inventory.getCount(gemId) > 0 && Inventory.getCount(goldBar) > 0) {
                if (furnace() != null) {
                    if (Calculations.distanceTo(furnaceTile) <= 4) {
                        if (furnace().isOnScreen()) {
                            if (Widgets.get(1371).validate()) {
                                status = "Clicking interface";
                                if (Widgets.get(1371).getChild(47).getChild(1).getAbsoluteY() > 176) {
                                    Widgets.get(1371).getChild(47).getChild(4).click(true);
                                } else {
                                    Widgets.get(1371).getChild(44).getChild(intid).click(true);
                                    methods.sleep(methods.rand(50, 100));
                                    Widgets.get(1370).getChild(20).click(true);
                                    methods.sleep(methods.rand(500, 800));
                                }
                            } else {
                                if (animationTimer.getRemaining() <= 0) {
                                    if (furnace().click(false)) {
                                        status = "Crafting";
                                        methods.sleep(methods.rand(50, 100));
                                        if (Menu.contains("Smelt")) {
                                            Menu.select("Smelt");
                                        }
                                        methods.sleep(methods.rand(50, 100));
                                    }
                                }
                            }
                        } else {
                            Camera.turnTo(furnace());
                        }
                    } else {
                        status = "Walking: furnace";
                        Path path = Walking.findPath(furnaceTile);
                        if (Calculations.distanceTo(furnaceTile) >= 4) {
                            path.traverse();
                        }
                    }
                }
            } else {
                if (Calculations.distanceTo(bankTile) <= 4) {
                    if (!Bank.isOpen()) {
                        status = "Opening bank";
                        Bank.open();
                        methods.sleep(methods.rand(300, 800));
                    } else {
                        if (Inventory.getCount() > Inventory.getCount(gemId) + Inventory.getCount(goldBar)) {
                            Bank.depositInventory();
                            methods.sleep(methods.rand(50, 100));
                            status = "Depositing";
                        } else {
                            status = "Checking items";
                            if (Inventory.getCount(gemId) > 14) {
                                Bank.deposit(gemId, Inventory.getCount(gemId - 14));
                                methods.sleep(methods.rand(50, 60));
                            }
                            if (Inventory.getCount(gemId) < 14) {
                                Bank.withdraw(gemId, 14 - Inventory.getCount(gemId));
                                methods.sleep(methods.rand(50, 60));
                            }
                            if (Inventory.getCount(goldBar) > 14) {
                                Bank.deposit(goldBar, Inventory.getCount(goldBar - 14));
                                methods.sleep(methods.rand(50, 60));
                            }
                            if (Inventory.getCount(goldBar) < 14) {
                                Bank.withdraw(goldBar, 14 - Inventory.getCount(goldBar));
                                methods.sleep(methods.rand(50, 60));
                            }
                            if (Inventory.getCount(gemId) == 14 && Inventory.getCount(goldBar) == 14) {
                                Bank.close();
                            }
                        }
                    }
                } else {
                    status = "Walking: bank";
                    Path p = Walking.findPath(bankTile);
                    if (Calculations.distanceTo(bankTile) >= 4) {
                        p.traverse();
                    }
                }
            }

            if (me().getAnimation() != -1) {
                animationTimer.reset();
            }

        }
        return 50;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(0, 0, 0, 124);
    private final Color color2 = new Color(255, 51, 51);
    private final Color color3 = new Color(255, 255, 255);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Tahoma", 0, 20);
    private final Font font2 = new Font("Tahoma", 0, 14);

    public void onRepaint(Graphics g1) {
        skillinfo.runTime = System.currentTimeMillis() - skillinfo.startTime;
        skillinfo.expGained = Skills.getExperience(Skills.CRAFTING) - skillinfo.startExp;
        skillinfo.expTNL = Skills.getExperienceToLevel(Skills.CRAFTING, skillinfo.level + 1);
        skillinfo.levelsGained = skillinfo.level - skillinfo.startLevel;
        skillinfo.timeTNL = (long) ((double) skillinfo.expTNL / (double) perHour(skillinfo.expGained) * 3600000);

        Graphics2D g = (Graphics2D) g1;
        g.setColor(color1);
        g.fillRect(524, 256, 236, 260);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(524, 256, 236, 260);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("Jewelry Maker", 553, 283);
        g.setFont(font2);
        g.drawString("Time Ran: " + timeFormat(skillinfo.runTime), 553, 316);
        g.drawString("Exp Gained: " + formatNum(skillinfo.expGained) + " (" + perHour(skillinfo.expGained) + ")", 553, 342);
        g.drawString("Level: " + skillinfo.level + " (" + skillinfo.levelsGained + ")", 553, 368);
        g.drawString("Location: " + loc, 553, 394);
        g.drawString("Item: " + item, 553, 422);
        g.drawString("TNL: " + timeFormat(skillinfo.timeTNL), 553, 450);
        g.drawString("Status: " + status, 553, 478);
        g.drawString("By: RedJack", 553, 506);
        g.drawImage(mouse, Mouse.getX(), Mouse.getY(), null);
    }
    //END: Code generated using Enfilade's Easel

    private int perHour(int arg0) {
        int num = (int) (3600000.0 / skillinfo.runTime * arg0);
        return num;
    }

    private String formatNum(int number) {
        return nf.format(number);
    }

    private String timeFormat(long duration) {
        String res = "";
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                .toHours(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                .toMinutes(duration));
        if (days == 0) {
            res = (hours + ":" + minutes + ":" + seconds);
        } else {
            res = (days + ":" + hours + ":" + minutes + ":" + seconds);
        }
        return res;
    }
}

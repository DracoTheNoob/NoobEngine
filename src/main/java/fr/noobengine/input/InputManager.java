package fr.noobengine.input;

import fr.noobengine.event.ClickEvent;
import fr.noobengine.event.EventManager;
import fr.noobengine.event.KeyEvent;
import fr.noobengine.math.Vector;

import javax.swing.event.MouseInputListener;
import java.awt.event.*;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, MouseInputListener {
    private final EventManager eventManager;

    private final boolean[] keys;
    private final boolean[] mouseButtons;
    private int wheelRotation;

    private boolean mouseOnPanel;
    private Vector mouseLocation;

    public InputManager(EventManager eventManager) {
        this.eventManager = eventManager;

        this.keys = new boolean[65535];
        this.mouseButtons = new boolean[6];
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        this.eventManager.proceed(new KeyEvent(e.getKeyCode(), true));
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        this.eventManager.proceed(new KeyEvent(e.getKeyCode(), false));
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.eventManager.proceed(new ClickEvent(e.getButton(), true));
        mouseButtons[e.getButton() - 1] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.eventManager.proceed(new ClickEvent(e.getButton(), false));
        mouseButtons[e.getButton() - 1] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.mouseOnPanel = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mouseOnPanel = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseLocation = new Vector(e.getX(), e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.wheelRotation = e.getWheelRotation();
    }


    public boolean isKeyPressed(int keyCode) { return keys[keyCode]; }
    public boolean isMouseButtonPressed(int button) { return mouseButtons[button - 1]; }
    public int getWheelRotation() { return wheelRotation; }
    public boolean isMouseOnPanel() { return mouseOnPanel; }
    public Vector getMouseLocation() { return mouseLocation; }

    // Not used functions
    @Override public void keyTyped(java.awt.event.KeyEvent e) {}
}

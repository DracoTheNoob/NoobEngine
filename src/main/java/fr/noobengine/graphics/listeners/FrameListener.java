package fr.noobengine.graphics.listeners;

import fr.noobengine.graphics.Frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameListener implements WindowListener {
    private final Frame frame;

    public FrameListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.frame.close();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

package fr.noobengine.event;

public non-sealed class KeyEvent extends Event {
    private final int keyCode;
    private final boolean pressed;

    public KeyEvent(int keyCode, boolean pressed) {
        this.keyCode = keyCode;
        this.pressed = pressed;
    }

    public int getKeyCode() { return keyCode; }
    public boolean isPressed() { return pressed; }
}

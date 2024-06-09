package fr.noobengine.event;

public non-sealed class ClickEvent extends Event {
    private final int button;
    private final boolean pressed;

    public ClickEvent(int button, boolean pressed) {
        this.button = button;
        this.pressed = pressed;
    }

    public int getButton() { return button; }
    public boolean isPressed() { return pressed; }
}

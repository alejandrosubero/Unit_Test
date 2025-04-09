package com.diagram;

public class ElementConnection {
    private DiagramElement target;
    private boolean canMove;

    public ElementConnection(DiagramElement target, boolean canMove) {
        this.target = target;
        this.canMove = canMove;
    }

    public DiagramElement getTarget() {
        return target;
    }

    public void setTarget(DiagramElement target) {
        this.target = target;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
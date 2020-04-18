package qdo.maze.exception;

import lombok.Getter;

@Getter
public class InvalidDirectionException extends RuntimeException {

    private int selectedPosition;

    public InvalidDirectionException(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}

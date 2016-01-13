package se.fredrikandthenurses.validation;

import se.fredrikandthenurses.model.AbstractEntity;

/**
 * Created by TheYellowBelliedMarmot on 2016-01-13.
 */
public interface Validator <T extends AbstractEntity>{

    public boolean isValid(T entity);
}

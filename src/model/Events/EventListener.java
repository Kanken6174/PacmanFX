package model.Events;

import model.Events.Events.Event;

public interface EventListener {
    public void HandleEvent(Event e);
}

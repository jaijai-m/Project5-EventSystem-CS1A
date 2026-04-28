package com.tribyte.swing.table;

import com.tribyte.model.ModelEvents;

public interface EventAction {
    public void delete(ModelEvents events);
    
    public void update(ModelEvents events);
}

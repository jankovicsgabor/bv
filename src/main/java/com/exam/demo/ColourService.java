package com.exam.demo;

import java.util.List;

public interface ColourService {
    void save(final Colour colour);
    List<Colour> findAll();
}

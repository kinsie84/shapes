package com.peter.shapes.services;

import com.peter.shapes.models.Shape;
import com.peter.shapes.models.Square;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public interface SquareService {

    Square createSquare(Square square);

    Optional<Square> getSquare(long id);

    List<Square> getAllSquares();

    Square updateSquare(long id, Square square);

    void deleteSquare(long id);

    void generateSquares(List<Shape> squares, int end);

    List<Square> getAllSquaresByArea(double area);


}

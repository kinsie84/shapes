package com.peter.shapes.services;

import com.peter.shapes.models.Shape;
import com.peter.shapes.models.Square;
import com.peter.shapes.repositories.SquareRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquareServiceImpl implements SquareService{

    @Autowired
    private SquareRepository squareRepository;

    @Override
    public Square createSquare(Square square) {
        return squareRepository.saveAndFlush(square);
    }

    @Override
    public Optional<Square> getSquare(long id) {
        return squareRepository.findById(id);
    }

    @Override
    public List<Square> getAllSquares() {
        return squareRepository.findAll();
    }

    @Override
    public Square updateSquare(long id, Square square) {
        Optional<Square> databaseVersion = getSquare(id);
        if(databaseVersion.isPresent()){
            BeanUtils.copyProperties(square,databaseVersion);
            return squareRepository.saveAndFlush(square);

        }
        throw new NullPointerException();
    }

    @Override
    public void deleteSquare(long id) {
        Optional<Square> square = getSquare(id);
        square.ifPresent(value -> squareRepository.delete(value));
    }

    /*
     * Takes in a List<Shape> along with an end point. Creates and persists new
     * square objects with randomly generated widths adds them to the list
     */
    @Override
    public void generateSquares(List<Shape> squares, int end) {
        if (squares != null) {
            for (int i = 0; i <= end; i++) {
                squares.add(createSquare(new Square(Math.random() * 100.0)));
            }
        }
    }

    /*
     * Takes a double and queries the database to find matches + or - 1.0 on
     * area field
     */
    @Override
    public List<Square> getAllSquaresByArea(double area) {
        return squareRepository.findSquaresByArea(area);
    }
}

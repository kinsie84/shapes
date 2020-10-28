package com.peter.shapes.controllers;

import com.peter.shapes.models.Square;
import com.peter.shapes.services.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shape/square")
public class SquareController {

    @Autowired
    private SquareService squareService;

    @PostMapping
    public Square create(@RequestBody Square square) throws Exception{

        if (square.getWidth() == 0.0) {
            throw new Exception("Cannot create a square without a width");
        }
        return squareService.createSquare(new Square(square.getWidth()));
    }

    @GetMapping("/{id}")
    public Optional<Square> get(@PathVariable("id") long id){
        return squareService.getSquare(id);
    }

    @GetMapping
    public List<Square> getAll(){
        return squareService.getAllSquares();
    }

    @PutMapping("/{id}")
    public Square update(@PathVariable("id") long id, @RequestBody Square square) throws Exception {
        if (square.getWidth() == 0.0) {
            throw new Exception("Cannot create square without a width");
        }
        try {
            return squareService.updateSquare(id, square);
        } catch (NullPointerException nullPointerException) {
            throw new Exception("Square with id: " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        squareService.deleteSquare(id);
    }





}



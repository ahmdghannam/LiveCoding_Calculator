package com.example.livecoding_calculator

enum class Operation {
    Plus,
    Minus,
    Multi,
    Div;

     override fun toString(): String {
       return when(this){
            Plus -> " +"
            Minus -> " -"
            Multi -> " x"
            Div -> " /"
        }
    }
}
cmake_minimum_required(VERSION 3.0)

project(lab1 VERSION 0.1 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)

include_directories("inc/")

set(SOURCES src/main.cpp 
            src/Algorithm.cpp 
            src/LevenshteinMemoisedAlgorithm.cpp
            src/LevenshteinRecursiveAlgorithm.cpp
            src/LevenshteinTableAlgorithm.cpp
            src/DamerauLevenshteinAlgorithm.cpp
            src/AlgorithmCreatorsBuilder.cpp
            src/Matrix.cpp
            src/Benchmark.cpp)

add_executable(lab1.exe ${SOURCES})
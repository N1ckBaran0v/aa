cmake_minimum_required(VERSION 3.29)
project(lab2)

set(CMAKE_CXX_STANDARD 20)

include_directories("inc/")

add_executable(lab2 src/main.cpp
        inc/Algorithm.h
        src/StandartAlgorithm.cpp
        inc/StandartAlgorithm.h
        src/Matrix.cpp
        inc/Matrix.h
        src/Matrix.cpp
        inc/StandartOptimisedAlgorithm.h
        src/StandartOptimisedAlgorithm.cpp
        inc/VinogradAlgorithm.h
        src/Algorithm.cpp
        src/VinogradAlgorithm.cpp
        src/VinogradOptimisedAlgorithm.cpp
        inc/VinogradOptimisedAlgorithm.h
        inc/AlgorithmCreator.h
        inc/Implementation.h
        inc/TemplateAlgorithmCreator.h
        inc/AlgorithmCreatorsBuilder.h
        src/AlgorithmCreatorsBuilder.cpp)

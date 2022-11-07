cmake_minimum_required(VERSION 3.18)
project(diamond_problem)

add_executable(${PROJECT_NAME} main.cpp)
target_sources(
        ${PROJECT_NAME}
        PRIVATE
        Vehicle.cpp Vehicle.h
        Car.cpp Car.h
        Boat.cpp Boat.h
        AmphibiousCar.cpp AmphibiousCar.h
)
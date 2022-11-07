cmake_minimum_required(VERSION 3.18)
project(virtual_methods_and_polymorphism)

set(LIBRARY_NAME "lib_${PROJECT_NAME}")

add_library(
        ${LIBRARY_NAME}
        STATIC
        Bicycle.cpp Bicycle.h
        Car.cpp Car.h
        Motorcycle.cpp Motorcycle.h
        Tricycle.cpp Tricycle.h
        Vehicle.h
)
target_include_directories(
        ${LIBRARY_NAME}
        INTERFACE
        ${CMAKE_CURRENT_SOURCE_DIR})

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} PUBLIC ${LIBRARY_NAME})

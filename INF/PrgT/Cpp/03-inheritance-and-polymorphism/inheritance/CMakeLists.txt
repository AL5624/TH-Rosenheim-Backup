cmake_minimum_required(VERSION 3.18)
project(inheritance)

set(LIBRARY_NAME "lib_${PROJECT_NAME}")

add_library(${LIBRARY_NAME} STATIC Circle.cpp Circle.h Position.h Shape.cpp Shape.h)
target_include_directories(${LIBRARY_NAME} INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} PUBLIC ${LIBRARY_NAME})
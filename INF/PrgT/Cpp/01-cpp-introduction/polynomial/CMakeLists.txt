cmake_minimum_required(VERSION 3.18)
project(polynomial)

set(LIBRARY_NAME "lib${PROJECT_NAME}")

add_library(${LIBRARY_NAME} STATIC polynomial.h polynomial.cpp)
target_include_directories(${LIBRARY_NAME} INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} PRIVATE ${LIBRARY_NAME})

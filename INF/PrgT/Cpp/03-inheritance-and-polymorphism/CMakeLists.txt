cmake_minimum_required(VERSION 3.18)
project(03_inheritance_and_polymorphism)

SET(CMAKE_CXX_STANDARD 17)

if(CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
    add_compile_options(-Wall -Wextra -Wdouble-promotion -pedantic)
endif()

add_subdirectory(diamond_problem)
add_subdirectory(inheritance)
add_subdirectory(virtual_methods_and_polymorphism)

### Unit Test Setup ###
# Workaround for running Google Test under Windows. Not to be used in a project if not specifically required
if(CMAKE_CXX_COMPILER_ID STREQUAL "MSVC")
    set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
endif()

include(GoogleTest)
add_subdirectory(googletest)
enable_testing()

add_subdirectory(tests)


### Pandoc setup ###
find_program(
        PANDOC_EXECUTABLE
        NAMES
        pandoc
        DOC "Pandoc executable location"
)

if(PANDOC_EXECUTABLE)
    MESSAGE(STATUS "Found Pandoc executable. Enabling target: readme-pdf.")

    add_custom_target(
            readme-pdf
            COMMAND
            pandoc ${PROJECT_SOURCE_DIR}/README.md
            -s
            -V lang=de
            -V papersize:a4paper
            -V geometry:margin=2.5cm
            --pdf-engine=lualatex
            -o "03 Inheritance and Polymorphism.pdf"
            --filter pandoc-plantuml
            VERBATIM
            COMMENT "Generating PDF from README.md"
    )

else()
    MESSAGE(STATUS "Pandoc executable not found. Target readme-pdf will be unavailable.")
endif()
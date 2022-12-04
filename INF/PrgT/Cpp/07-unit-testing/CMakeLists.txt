cmake_minimum_required(VERSION 3.18)
project(07_unit_testing)

set(CMAKE_CXX_STANDARD 17)

if(CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
    add_compile_options(-Wall -Wextra -Wdouble-promotion -pedantic)
endif()


find_package(
        Boost
        1.74
        REQUIRED
        COMPONENTS
        unit_test_framework
)


### Unit Test Setup ###

add_subdirectory(Catch2)
list(APPEND CMAKE_MODULE_PATH ${Catch2_SOURCE_DIR}/contrib)
include(CTest)
include(Catch)

# Workaround for running Google Test under Windows.
if(CMAKE_CXX_COMPILER_ID STREQUAL "MSVC")
    set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
endif()

include(GoogleTest)
add_subdirectory(googletest)
enable_testing()

add_subdirectory(multiply)
add_subdirectory(student)

add_subdirectory(test)


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
            -o "07 Unit Testing.pdf"
            --filter pandoc-plantuml
            VERBATIM
            COMMENT "Generating PDF from README.md"
    )

else()
    MESSAGE(STATUS "Pandoc executable not found. Target readme-pdf will be unavailable.")
endif()
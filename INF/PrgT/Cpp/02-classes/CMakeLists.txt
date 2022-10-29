cmake_minimum_required(VERSION 3.18)
project(02_classes)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(dice_game)
add_subdirectory(car_exchange)
add_subdirectory(structures)
add_subdirectory(unions)

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
            -o "02 Classes.pdf"
            --filter pandoc-plantuml
            VERBATIM
            COMMENT "Generating PDF from README.md"
    )

else()
    MESSAGE(STATUS "Pandoc executable not found. Target readme-pdf will be unavailable.")
endif()
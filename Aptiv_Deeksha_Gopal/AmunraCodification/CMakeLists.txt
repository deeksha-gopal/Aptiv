cmake_minimum_required (VERSION 3.10)
project (AmunraCodification CXX)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

add_compile_options(-Wall -Wextra -Wshadow -Wcast-align -Wunused -Wconversion -fstack-protector-strong)

if(WARNINGS_AS_ERRORS)
    add_compile_options(-Werror)
endif()


add_subdirectory(src)

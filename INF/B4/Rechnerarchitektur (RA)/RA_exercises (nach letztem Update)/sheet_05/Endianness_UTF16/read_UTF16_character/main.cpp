#include <stdlib.h> //EXIT_SUCCESS
#include <string>   //std::string, char16t
#include <iostream> //std::cout

#include <locale>   // wstring_convert
#include <codecvt>  // codecvt_utf8

//prototypes
void print_utf16_string(const std::u16string& message, 
                        std::u16string utf16_character);
char16_t swap(const char16_t character);


int main(void) {
    char16_t message[] = u"A nuclear power plant is ";
    char16_t utf16_character = u' ';

    //read utf16 character from file
    FILE* file = NULL;
    file = fopen("../utf16_character_be.txt", "rb");
        
    if (file == NULL) {
        std::cerr << "Error opening: " << "utf16_character_be.txt" << std::endl;
        return EXIT_FAILURE;
    }

    fread(&utf16_character, sizeof(uint16_t), 1, file);
    fclose(file);

    //TODO: fix the byte order (you may call the swap() function for that)


    //print the message with the utf16 character
    print_utf16_string(message, std::u16string{utf16_character});

    return EXIT_SUCCESS;
}

void print_utf16_string(const std::u16string& message, 
                        std::u16string utf16_character) {
    std::wstring_convert<std::codecvt_utf8<char16_t>, char16_t> convert;

    std::cout << convert.to_bytes(message) 
              << convert.to_bytes(utf16_character) << std::endl;

    //doesn't work: utf16 char isn't printed correctly
//    std::wcout << std::wstring(message.begin(), message.end()) 
//               << std::wstring(utf16_character.begin(), utf16_character.end()) << std::endl;
}

char16_t swap(const char16_t character) {
    union {
        char16_t c;
        char bytes[2];
    } SWAP_TYPE;

    //TODO: fix the byte order (you may use the union for that)

    return ; //TODO: return the swapped character value
}
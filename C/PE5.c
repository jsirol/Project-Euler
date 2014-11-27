#include <stdio.h>

// find the smallest positive number that is evenly divisible with 1:20
int main(int argc, char** argv) {
    int not_found = 1;
    int number = 1;
    while(not_found) {
        for(int ii=2; ii<=20; ii++) {
            if(number%ii!=0) {
                break;
            }
            if(ii==20) {
                not_found = 0;
            }
        }
        if(not_found) {
            number += 1;
        }
    }
    printf("%d\n",number);
    return 0;
}
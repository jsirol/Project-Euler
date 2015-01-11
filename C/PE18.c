/* 
 * File:   main.c
 * Author: Johannes
 *
 * Created on 25. marraskuuta 2014, 18:28
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

unsigned long max(unsigned long a, unsigned long b) {
    if (a >= b) return a;
    return b;
}

void max_path(int rows, char* fname) {
    printf("Reading data...\n");
    // initialize variables
    clock_t t;
    unsigned int row = 0, col = 0, i = 0, j = 0, curr = 0;
    unsigned long **n = malloc(rows * sizeof (unsigned long*));
    for (i = 0; i < rows; i++) {
        n[i] = malloc((i+1) * sizeof (unsigned long));
    }
    t = clock();
    
    // get file size
    FILE *f = fopen(fname, "r");
    fseek(f, 0L, SEEK_END);
    int size = ftell(f);
    fseek(f, 0L, SEEK_SET);

    // initialize and allocate space for buffer
    char *buffer;
    if ((buffer = malloc(size * sizeof (char))) == NULL) {
        printf("memory allocation error!\n");
        exit(0);
    }
    buffer = memset(buffer, 0, sizeof (buffer));
    char *buffer_start = buffer;
    
    // read file to buffer
    fread(buffer, sizeof (char), size, f);
    // process buffer
    while (curr = strtol(buffer, &buffer, 10)) {
        n[row][col] = (unsigned long) curr;
        col++;
        if (col > row) {
            col = 0;
            row++;
            if (row == rows) {
                break;
            }
        }
    }
    buffer = buffer_start;
    free(buffer);
    fclose(f);
    t = clock() - t;
    // table (n) for dynamic program constructed
    // now calculate result
    printf("Reading complete, took %f seconds\n", ((float) t) / CLOCKS_PER_SEC);
    printf("Calculating result...\n");
    for (i = 1; i < rows; i++) {
        for (j = 0; j <= i; j++) {
            if (0 < j && j < i) {
                n[i][j] += max(n[i - 1][j - 1], n[i - 1][j]);
            } else if (j == 0) {
                n[i][j] += n[i - 1][j];
            } else {
                n[i][j] += n[i - 1][j - 1];
            }
        }
    }
    // find max value from the last row
    unsigned long mm = 0;
    for (i = 0; i < rows; i++) {
        mm = max(mm, n[rows - 1][i]);
    }
    t = clock() - t;
    printf("Calculation complete, took %f seconds\n", ((float) t) / CLOCKS_PER_SEC);
    printf("max route cost: %lu", mm);
    // free allocated memory
    for (i = 0; i < rows; i++) {
        free(n[i]);
    }
    free(n);
}

int main(int argc, char** argv) {
    char* fname = "pp.txt";
    // set argument to be the number of rows and remember to change filename
    max_path(15, fname); 
    return (0);
}
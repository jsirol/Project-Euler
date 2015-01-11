/* 
 * File:   main.c
 * Author: Johannes
 *
 * Created on 25. marraskuuta 2014, 18:28
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

unsigned long max(unsigned long a,unsigned long b)
{
    if(a >= b) return a;
    return b;
}


int main(int argc, char** argv) 
{
    unsigned int row = 0, col=0, count=0, i, j;
    unsigned long **n = malloc(20000*sizeof(int*));
    for(i=0; i< 20000; i++) 
    {
        n[i] = malloc(20000*sizeof(unsigned long));
    }
    FILE *f = fopen("asd3.txt","r");
    while(!feof(f))
    {
        fscanf(f,"%d",&n[row][col]);
        count++;
        col++;
        if(count == row + 1)
        {
            row++;
            col = 0;
            count = 0;
        }
    }
    for(i=1; i<20000; i++)
    {
        for(j=0; j<i; j++)
        {
            if(0 < j && j < i)
            {
                n[i][j] += max(n[i-1][j-1],n[i-1][j]);
            } 
            else if(j==0)
            {
                n[i][j] +=n[i-1][j];
            }
            else
            {
                n[i][j] += n[i-1][j-1];
            }
        }
    }
    unsigned long mm = 0;
    for(i=0; i<20000; i++)
    {
        mm = max(mm,n[19999][i]);
    }
    for(i=0; i < 20000; i++) 
    {
        free(n[i]);
    }
    free(n);
    printf("max route cost: %lu",mm);
    fclose(f);
    return (0);
}
package com.test;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.util.Scanner;
 
public class toxicWater {
	public static final int waterNumber = 15;//水的数目
	public static final int mouseNumber = 4;//老鼠的数目
	
	public static void main(String args[]){
		int water[] = new int[waterNumber];
		int mouse[] = new int[mouseNumber];
		int i,j;
		String s;
		StringBuffer sb;
		int flag = 1;
		
		Scanner reader = new Scanner(System.in);
//		System.out.println("请输入老鼠的存活情况，1 表示死了，0表示还活着：");
		for( i=0; i<mouseNumber; i++){
			System.out.println("请输入第"+i+"老鼠的存活情况(1 表示死了，0表示还活着)");
			mouse[i] = reader.nextInt();
		}
		for( i=0; i<mouseNumber; i++ ){//mouseNumber只老鼠
			for( j=0; j<waterNumber; j++ ){//1000瓶水
				s = Long.toBinaryString(j);//将 j 转换为二进制
				sb = new StringBuffer(s);
				sb.reverse();//将字符串反转
				if( sb.length() >= i+1 ){//字符串长度
					if( mouse[i] == 1 ){//老鼠死了
						if( sb.charAt(i) == '1'){//第i只老鼠喝了第j瓶水
							water[j]--;
						}
					}
					else{//老鼠未死
						if( sb.charAt(i) == '1'){
							water[j]++;
						}
					}
				}
			}
		}
		int min = 0;
		for( i=0; i<waterNumber; i++ ){//找出数值最小的water
			if( water[i] < min ){
				min = water[i];
				flag = i;
			}
		}
		if( min < 0 ){
			System.out.println("第瓶"+flag+"瓶水有毒!!!");
		}
		else{
			System.out.println("第瓶0瓶水有毒!!!");
		}
	}
}

package com.test;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.util.Scanner;
 
public class toxicWater {
	public static final int waterNumber = 15;//ˮ����Ŀ
	public static final int mouseNumber = 4;//�������Ŀ
	
	public static void main(String args[]){
		int water[] = new int[waterNumber];
		int mouse[] = new int[mouseNumber];
		int i,j;
		String s;
		StringBuffer sb;
		int flag = 1;
		
		Scanner reader = new Scanner(System.in);
//		System.out.println("����������Ĵ�������1 ��ʾ���ˣ�0��ʾ�����ţ�");
		for( i=0; i<mouseNumber; i++){
			System.out.println("�������"+i+"����Ĵ�����(1 ��ʾ���ˣ�0��ʾ������)");
			mouse[i] = reader.nextInt();
		}
		for( i=0; i<mouseNumber; i++ ){//mouseNumberֻ����
			for( j=0; j<waterNumber; j++ ){//1000ƿˮ
				s = Long.toBinaryString(j);//�� j ת��Ϊ������
				sb = new StringBuffer(s);
				sb.reverse();//���ַ�����ת
				if( sb.length() >= i+1 ){//�ַ�������
					if( mouse[i] == 1 ){//��������
						if( sb.charAt(i) == '1'){//��iֻ������˵�jƿˮ
							water[j]--;
						}
					}
					else{//����δ��
						if( sb.charAt(i) == '1'){
							water[j]++;
						}
					}
				}
			}
		}
		int min = 0;
		for( i=0; i<waterNumber; i++ ){//�ҳ���ֵ��С��water
			if( water[i] < min ){
				min = water[i];
				flag = i;
			}
		}
		if( min < 0 ){
			System.out.println("��ƿ"+flag+"ƿˮ�ж�!!!");
		}
		else{
			System.out.println("��ƿ0ƿˮ�ж�!!!");
		}
	}
}

#include <string.h>
#include <jni.h>
#include <stdio.h>

#define APP_PACKAGE "com.example.dheeraj.jnitest"

int s_ButtonPressCounter = 0;

jstring Java_com_example_dheeraj_jnitest_MainActivity_stringFromJNI(JNIEnv* env,jobject thiz,jstring in,jstring out)
{
	char * inputFile = (char *)(*env)->GetStringUTFChars( env, in , NULL ) ;
	char * outputFile = (char *)(*env)->GetStringUTFChars( env, out , NULL ) ;

    char * finalCommand = (char*)malloc(sizeof(char)*5000);

	strcpy(finalCommand, "/system/bin/cp");
	strcat(finalCommand, " ");
	strcat(finalCommand, inputFile);
	strcat(finalCommand, " ");
	strcat(finalCommand, outputFile);
	system(finalCommand);

    jstring result;
    result = (*env)->NewStringUTF(env,finalCommand);
    return result;
}
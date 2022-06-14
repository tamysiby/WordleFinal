#include <jni.h>
#include <string>
#include <fcntl.h>
#include <unistd.h>
#include <iostream>
#include <fstream>

//FULLCOLORLED
#define FULL_LED1	9
#define	FULL_LED2	8
#define FULL_LED3	7
#define FULL_LED4	6
#define ALL_LED		5

//TEXT LCD
#define TEXTLCD_ON 		1
#define TEXTLCD_OFF 	2
#define TEXTLCD_INIT 	3
#define TEXTLCD_CLEAR		4
#define TEXTLCD_LINE1		5
#define TEXTLCD_LINE2		6

extern "C" JNIEXPORT jstring JNICALL
Java_kr_ac_dankook_finalproj5_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello changed?";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_Board_LedControl(JNIEnv *env, jobject thiz, jint data) {
    // TODO: implement LedControl()
    int fd;

    fd = open("/dev/fpga_led", O_WRONLY);
    assert(fd != 0);

    write(fd, &data, 1);
    close(fd);

}

extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_DotMatrix_DotMatrixControl(JNIEnv *env, jobject thiz, jstring str) {
    // TODO: implement DotMatrixControl()
    const char *pStr;
    int fd, len;

    pStr = env->GetStringUTFChars(str, 0);
    len = env->GetStringLength(str);

    fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);

    write(fd, pStr, len);
    close(fd);

    env->ReleaseStringUTFChars(str, pStr);
}

extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_Led_on(JNIEnv *env, jobject thiz, jchar data) {
    // TODO: implement on()
    int fd;

    fd = open("/dev/fpga_led", O_WRONLY);
    assert(fd != 0);

    write(fd, &data, 1);
    close(fd);
}

extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_FLed_FLEDControl(JNIEnv *env, jobject thiz, jint led_num, jint val1,
                                               jint val2, jint val3) {
    // TODO: implement FLEDControl()
    int fd,ret;
    char buf[3];

    fd = open("/dev/fpga_fullcolorled", O_WRONLY);
    if (fd < 0)
    {
        exit(-1);
    }
    ret = (int)led_num;
    switch(ret)
    {
        case FULL_LED1:
            ioctl(fd,FULL_LED1);
            break;
        case FULL_LED2:
            ioctl(fd,FULL_LED2);
            break;
        case FULL_LED3:
            ioctl(fd,FULL_LED3);
            break;
        case FULL_LED4:
            ioctl(fd,FULL_LED4);
            break;
        case ALL_LED:
            ioctl(fd,ALL_LED);
            break;
    }
    buf[0] = val1;
    buf[1] = val2;
    buf[2] = val3;

    write(fd,buf,3);

    close(fd);
}



//============================= TEXT LCD ===================================

static int fd_tlcd;
extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_TextLCD_on(JNIEnv *env, jobject thiz) {
    // TODO: implement on()
    if (fd_tlcd == 0)
        fd_tlcd = open("/dev/fpga_textlcd", O_WRONLY);
    assert(fd_tlcd != 0);

    ioctl(fd_tlcd, TEXTLCD_ON);
}
extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_TextLCD_off(JNIEnv *env, jobject thiz) {
    // TODO: implement off()
    if (fd_tlcd)
    {
        ioctl(fd_tlcd, TEXTLCD_OFF);
        close(fd_tlcd);
    }

    fd_tlcd= 0;
}


extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_TextLCD_initialize(JNIEnv *env, jobject thiz) {
    // TODO: implement initialize()
    if (fd_tlcd == 0)
        fd_tlcd = open("/dev/fpga_textlcd", O_WRONLY);
    assert(fd_tlcd != -1);

    ioctl(fd_tlcd, TEXTLCD_INIT);
}
extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_TextLCD_clear(JNIEnv *env, jobject thiz) {
    // TODO: implement clear()
    ioctl(fd_tlcd, TEXTLCD_CLEAR);
}
extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_TextLCD_print1Line(JNIEnv *env, jobject thiz, jstring msg) {
    // TODO: implement print1Line()
    const char *str;

    if (fd_tlcd )
    {
        str = env->GetStringUTFChars(msg, 0);
        ioctl(fd_tlcd, TEXTLCD_LINE1);
        write(fd_tlcd, str, strlen(str));
        env->ReleaseStringUTFChars(msg, str);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_TextLCD_print2Line(JNIEnv *env, jobject thiz, jstring msg) {
    // TODO: implement print2Line()
    const char *str;

    if (fd_tlcd )
    {
        str = env->GetStringUTFChars(msg, 0);
        ioctl(fd_tlcd, TEXTLCD_LINE2);
        write(fd_tlcd, str, strlen(str));
        env->ReleaseStringUTFChars(msg, str);
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_kr_ac_dankook_finalproj5_BoardComponents_DotMatrix_DotMatrixControl(JNIEnv *env, jobject thiz,
                                                                         jstring str) {
    // TODO: implement DotMatrixControl()
    // TODO: implement DotMatrixControl()
    const char *pStr;
    int fd, len;

    pStr = env->GetStringUTFChars(str, 0);
    len = env->GetStringLength(str);

    fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);

    write(fd, pStr, len);
    close(fd);

    env->ReleaseStringUTFChars(str, pStr);
}
package LeetCode.svm;

import java.io.IOException;

public class test {

    /*
        svm-train
        用法： svm_train [options] training_set_file [model_file]
            options为操作参数
                -s 设置svm类型：默认值为0
                    0 – C-SVC
                    1 – v-SVC
                    2 – one-class-SVM
                    3 –ε-SVR
                    4 – n - SVR

                -t 设置核函数类型，默认值为2
                    0 --线性核：u’v
                    1 --多项式核：(gu’v+coef0)degree
                    2 – RBF核：exp(-γ||u-v||2)
                    3 – sigmoid核：tanh(γ*u’*v+coef0)

                -d degree:设置多项式核中degree的值，默认为3

                -gγ:设置核函数中γ的值，默认为1/k，k为特征（或者说是属性）数；

                -r coef 0:设置核函数中的coef 0，默认值为0；

                -c cost：设置C-SVC、ε-SVR、n - SVR中从惩罚系数C，默认值为1；

                -n v：设置v-SVC、one-class-SVM与n - SVR中参数n，默认值0.5；

                -pε：设置v-SVR的损失函数中的e，默认值为0.1；

                -m cachesize：设置cache内存大小，以MB为单位，默认值为40；

                -eε：设置终止准则中的可容忍偏差，默认值为0.001；

                -h shrinking：是否使用启发式，可选值为0或1，默认值为1；

                -b概率估计：是否计算SVC或SVR的概率估计，可选值0或1，默认0；

                -wi weight：对各类样本的惩罚系数C加权，默认值为1；

                -v n：n折交叉验证模式；

                model_file：可选项，为要保存的结果文件，称为模型文件，以便在预测时使用。
                为了能保存结果，还是要提供一个结果文件名如line.txt，poly.txt，RBF.txt
     */
    public static void main(String[] args) throws IOException {
        String filepath = "D:\\Desktop\\";//自己选择路径

        //train
        String[] arg = {"-v","10","-c","10","-t","0",filepath+"model.txt",filepath+"line.txt"};
        System.out.println("----------------线性-----------------");
        //训练函数
        svm_train.main(arg);

        arg[5]="1";
        arg[7]=filepath+"poly.txt";//输出文件路径
        System.out.println("---------------多项式-----------------");
        svm_train.main(arg);

        arg[5]="2";
        arg[7]=filepath+"RBF.txt";
        System.out.println("---------------高斯核-----------------");
        svm_train.main(arg);

    }

    /**
     * #iter为迭代次数，
     *
     * nu 与前面的操作参数-n nu 相同，
     *
     * obj为SVM文件转换为的二次规划求解得到的最小值，
     *
     * rho 为判决函数的常数项b，
     *
     * nSV 为支持向量个数，
     *
     * nBSV为边界上的支持向量个数，
     *
     * Total nSV为支持向量总个数。
     */

}

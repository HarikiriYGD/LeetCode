package LeetCode.svm;

import libsvm.svm;

import java.io.IOException;

public class predict {

    public static void main(String[] args) throws IOException {
        String filepath = "D:\\Desktop\\";//自己选择路径
//predict
        String[] arg1 = {filepath + "test.txt", filepath + "line.txt", filepath + "line_res.txt"};
        System.out.println("----------------线性-----------------");
        //预测函数
        svm_predict.main(arg1);

        arg1[1] = filepath + "poly.txt"; //输入文件模型路径
        arg1[2] = filepath + "poly-res.txt";//输出文件结果路径
        System.out.println("---------------多项式-----------------");
        svm_predict.main(arg1);

        arg1[1] = filepath + "RBF.txt";
        arg1[2] = filepath + "RBF_res.txt";
        System.out.println("---------------高斯核-----------------");
        svm_predict.main(arg1);
    }

}

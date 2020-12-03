package com.example.tubes_kelg_c;

import java.util.ArrayList;

public class DaftarMotor {
    public ArrayList<Motor> MOTOR = new ArrayList();

    public DaftarMotor(){
        MOTOR.add(VARIO);
        MOTOR.add(BEAT);
        MOTOR.add(VESPA);
        MOTOR.add(MIO);
        MOTOR.add(NMAX);
        MOTOR.add(PCX);
        MOTOR.add(REVO);

    }

    public static final Motor VARIO = new Motor("Honda Vario 125", "Matic",
            "Rp 80.000");

    public static final Motor BEAT = new Motor("Honda Beat", "Matic",
            "Rp 70.000");

    public static final Motor VESPA = new Motor("Vespa", "Matic",
            "Rp 150.000");

    public static final Motor MIO = new Motor("Yamaha Mio", "Matic",
            "Rp 60.000");

    public static final Motor NMAX = new Motor("Yamaha NMAX", "Matic",
            "Rp 120.000");

    public static final Motor PCX = new Motor("Honda PCX", "Matic",
            "Rp 130.000");

    public static final Motor REVO = new Motor("Honda Revo", "Manual",
            "Rp 70.000");

}

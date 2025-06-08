package org.firstinspires.ftc.teamcode.pedroPathing.examples;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous(name = "TRY")
public class TRY extends OpMode {
    private Follower follower;
    private Telemetry telemetryA;
    private PathChain path;
    private Path line1 = new Path(
            new BezierCurve(
                    new Point(28.901, 43.183, Point.CARTESIAN),
                    new Point(44.191, 44.863, Point.CARTESIAN),
                    new Point(42.511, 56.289, Point.CARTESIAN)
            )
    );
    private Path line2 = new Path(
            new BezierCurve(
                    new Point(42.511, 56.289, Point.CARTESIAN),
                    new Point(27.557, 53.937, Point.CARTESIAN),
                    new Point(28.901, 70.572, Point.CARTESIAN)
            )
    );
    private PathChain line12 = new PathChain(
            line1,line2
    );
    private final Pose startPose = new Pose(0,0, Math.toRadians(0));
    @Override
    public void init() {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        path = follower.pathBuilder()
                .addPath(line1)
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .addPath(
                        new BezierCurve(
                                new Point(42.511, 56.289, Point.CARTESIAN),
                                new Point(27.557, 53.937, Point.CARTESIAN),
                                new Point(28.901, 70.572, Point.CARTESIAN)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        follower.followPath(path);

        telemetryA = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetryA.update();
    }

    @Override
    public void loop() {
        follower.update();
        if (follower.atParametricEnd()) {
            follower.followPath(path);
        }

        follower.telemetryDebug(telemetryA);
    }

}

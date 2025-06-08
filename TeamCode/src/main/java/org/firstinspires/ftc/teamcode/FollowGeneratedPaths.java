package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous(name = "Follow Generated Paths (Loop-Based)")
public class FollowGeneratedPaths extends OpMode {

    private Follower follower;
    private FtcDashboard dashboard;

    private boolean startedLine2 = false;

    @Override
    public void init() {
        // Apply constants for localization/drivetrain
        Constants.setConstants(FConstants.class, LConstants.class);

        // Initialize Follower and FTC Dashboard
        follower = new Follower(hardwareMap);
        dashboard = FtcDashboard.getInstance();

        // Set starting pose to first point of line1
        follower.setStartingPose(new Pose(0, 0, Math.toRadians(0)));

        // Start with the first path
        follower.followPath(K.line1);

        // Combined telemetry for DS + Dashboard
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }

    @Override
    public void loop() {
        // Continuously update the follower
        follower.update();

        // If line1 is done, follow line2 (once)
        if (!startedLine2 && follower.atParametricEnd()) {
            follower.followPath(K.line2);
            startedLine2 = true;
        }

        // Show robot path + pose on the Dashboard
        follower.telemetryDebug(telemetry);
    }

    // Static class containing generated paths
    static class K {
        public static PathBuilder builder = new PathBuilder();

        public static PathChain line1 = builder
                .addPath(
                        new BezierCurve(
                                new Point(72.000 - 72, 72.000 - 72, Point.CARTESIAN),
                                new Point(44.359 - 72, 45.368 - 72, Point.CARTESIAN),
                                new Point(39.991 - 72, 81.662 - 72, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        public static PathChain line2 = builder
                .addPath(
                        new BezierCurve(
                                new Point(39.991 - 72, 81.662 - 72, Point.CARTESIAN),
                                new Point(39.151 - 72, 124.509 - 72, Point.CARTESIAN),
                                new Point(132.000 - 72, 102.000 - 72, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
    }
}

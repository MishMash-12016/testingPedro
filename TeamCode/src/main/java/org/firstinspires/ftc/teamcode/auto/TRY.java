package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.commands.FollowPath;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@Autonomous(name = "TRY")
public class TRY extends OpMode {
    private Follower follower;
    private Telemetry telemetryA;
    private PathChain path;
    private static final Pose startPose = new Pose(72,72, Math.toRadians(0));
    @Override
    public void init() {
        schedule(
                new InstantCommand(),
                new FollowPath({ROBOT},path1())
        );

    }

    @Override
    public void loop() {
        follower.update();
        if (follower.atParametricEnd()) {
            follower.followPath(path);
        }

        follower.telemetryDebug(telemetryA);
    }

    public static PathChain path1() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(72.000, 72.000, Point.CARTESIAN),
                                new Point(110.000, 72.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .setReversed(true)
                .build();
    }

}

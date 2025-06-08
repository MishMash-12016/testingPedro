package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import java.util.ArrayList;
class GeneratedPaths {

    public static PathBuilder builder = new PathBuilder();

    public static PathChain line1 = builder
            .addPath(
                    new BezierCurve(
                            new Point(28.901, 43.183, Point.CARTESIAN),
                            new Point(44.191, 44.863, Point.CARTESIAN),
                            new Point(42.511, 56.289, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
            .build();

    public static PathChain line2 = builder
            .addPath(
                    new BezierCurve(
                            new Point(42.511, 56.289, Point.CARTESIAN),
                            new Point(27.557, 53.937, Point.CARTESIAN),
                            new Point(28.901, 70.572, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
            .build();
}

@Autonomous
public class FollowPedroSample extends CommandOpMode {
    Follower follower = new Follower(hardwareMap);
    private final ArrayList<PathChain> paths = new ArrayList<>();
    PathChain pathChain = new PathChain();


    @Override
    public void initialize() {
        super.reset();

        schedule(
                // Updates follower to follow path
                new RunCommand(() -> follower.update()),

                new FollowPathCommand(follower, paths.get(0)),
                new FollowPathCommand(follower, pathChain)
//                new FollowPathCommand(follower, path)
        );
    }

    @Override
    public void run() {
        super.run();

    }
}
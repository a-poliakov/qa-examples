package org.example.service;

import org.example.model.Robot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RobotServiceImplTest {
    @Mock
    private RobotDao robotDAO;

    private RobotServiceImpl robotService;

    private static Robot testRobot;

    @BeforeClass
    public static void prepareTestData() {
        testRobot = Robot
                .builder()
                .id(123L)
                .name("testRobotMolly")
                .cpu("Intel Core i7-9700K")
                .producer("China")
                .build();
    }

    @Before
    public void init() {
        robotService = new RobotServiceImpl(robotDAO);
    }

    @Test
    public void updateTest() {
        when(robotDAO.findById(any(Long.class))).thenReturn(Optional.ofNullable(testRobot));
        when(robotDAO.save(any(Robot.class))).then(returnsFirstArg());
        Robot robotForUpdate = Robot
                .builder()
                .name("Vally")
                .cpu("AMD Ryzen 7 2700X")
                .build();

        Robot resultRobot = robotService.update(123L, robotForUpdate);

        assertNotNull(resultRobot);
        assertSame(resultRobot.getId(),testRobot.getId());
        assertThat(resultRobot.getName()).isEqualTo(robotForUpdate.getName());
        assertEquals(resultRobot.getCpu(), robotForUpdate.getCpu());
        assertEquals(resultRobot.getProducer(),testRobot.getProducer());
    }
}
